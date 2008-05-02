/**

 * InterestAccountingEntry.java    version: 1.0

 

 * Copyright (c) 2005-2006 Grameen Foundation USA

 * 1029 Vermont Avenue, NW, Suite 400, Washington DC 20005

 * All rights reserved.

 

 * Apache License 
 * Copyright (c) 2005-2006 Grameen Foundation USA 
 * 

 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
 *

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the 

 * License. 
 * 
 * See also http://www.apache.org/licenses/LICENSE-2.0.html for an explanation of the license 

 * and how it is applied.  

 *

 */

package org.mifos.application.accounts.financial.business.service.activity.accountingentry;

import org.mifos.application.accounts.financial.business.FinancialActionBO;
import org.mifos.application.accounts.financial.business.GLCodeEntity;
import org.mifos.application.accounts.financial.exceptions.FinancialException;
import org.mifos.application.accounts.financial.util.helpers.FinancialActionCache;
import org.mifos.application.accounts.financial.util.helpers.FinancialActionConstants;
import org.mifos.application.accounts.financial.util.helpers.FinancialConstants;
import org.mifos.application.accounts.loan.business.LoanBO;
import org.mifos.application.accounts.loan.business.LoanTrxnDetailEntity;
import org.mifos.framework.util.helpers.Money;

public class InterestAccountingEntry extends BaseAccountingEntry {
	@Override
	protected void getSpecificAccountActionEntry() throws FinancialException {
		LoanTrxnDetailEntity loanTrxn = (LoanTrxnDetailEntity) financialActivity
				.getAccountTrxn();
		GLCodeEntity glcodeCredit = ((LoanBO) loanTrxn.getAccount())
				.getLoanOffering().getInterestGLcode();

		FinancialActionBO finActionInterest = FinancialActionCache
				.getFinancialAction(FinancialActionConstants.INTERESTPOSTING);
		addAccountEntryDetails(loanTrxn.getInterestAmount(), finActionInterest,
				getGLcode(finActionInterest.getApplicableDebitCharts()),
				FinancialConstants.DEBIT);

		addAccountEntryDetails(loanTrxn.getInterestAmount(), finActionInterest,
				glcodeCredit, FinancialConstants.CREDIT);
		
		LoanBO loan =  (LoanBO)loanTrxn.getAccount();
		// the new version of financial calculation will log 999 account to interest account
		if (!loan.isLegacyLoan())
		{
			boolean isLastPayment = ((LoanBO)loanTrxn.getAccount()).isLastInstallment(loanTrxn.getInstallmentId());
			if (isLastPayment)
			{
				log999Account(loanTrxn, isLastPayment, glcodeCredit);
			}
		}

	}
	
	private void log999Account(LoanTrxnDetailEntity loanTrxn, boolean isLastPayment, GLCodeEntity glcodeCredit) throws FinancialException
	{
	
		
		Money account999 = ((LoanBO)loanTrxn.getAccount()).calculate999Account(isLastPayment);
		Money zeroAmount = new Money("0");
		// only log if amount > or < 0
		if (account999.equals(zeroAmount))
		{
			return;
		}
		
		FinancialActionBO finActionRounding = FinancialActionCache
		.getFinancialAction(FinancialActionConstants.ROUNDING);
		GLCodeEntity codeToDebit = null; 
		GLCodeEntity codeToCredit = null;
		if (account999.getAmountDoubleValue() > 0)
		{
			// this code is defined as below in chart of account 
			// <GLAccount code="31401" name="Income from 999 Account" />
			codeToDebit = glcodeCredit;
			codeToCredit = getGLcode(finActionRounding.getApplicableCreditCharts()); 
			
		}
		else if (account999.getAmountDoubleValue() < 0)
		{
			codeToDebit = getGLcode(finActionRounding.getApplicableDebitCharts());
			codeToCredit = glcodeCredit;
			account999 = account999.negate();
		}
		addAccountEntryDetails(account999, finActionRounding, codeToDebit, FinancialConstants.DEBIT);
		addAccountEntryDetails(account999, finActionRounding, codeToCredit, FinancialConstants.CREDIT);	
			
		
	}

}
