/*
 * Copyright (c) 2005-2010 Grameen Foundation USA
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 *
 * See also http://www.apache.org/licenses/LICENSE-2.0.html for an
 * explanation of the license and how it is applied.
 */

package org.mifos.test.acceptance.framework.savings;

import org.mifos.test.acceptance.framework.AbstractPage;

import com.thoughtworks.selenium.Selenium;

public class CreateSavingsAccountEntryPage extends AbstractPage {

    public void verifyPage() {
        this.verifyPage("continuecreatesavingsaccount");
    }

    public CreateSavingsAccountEntryPage(Selenium selenium) {
        super(selenium);
    }

    public CreateSavingsAccountConfirmationPage submitAndNavigateToSavingsAccountConfirmationPage(CreateSavingsAccountSubmitParameters formParameters) {
        selenium.type("continuecreatesavingsaccount.input.recommendedAmount",formParameters.getAmount());
        selenium.click("continuecreatesavingsaccount.button.preview");
        waitForPageToLoad();
        selenium.isVisible("createsavingsaccountpreview.button.submitForApproval");
        selenium.click("createsavingsaccountpreview.button.submitForApproval");
        waitForPageToLoad();
        return new CreateSavingsAccountConfirmationPage(selenium);

    }

    public CreateSavingsAccountConfirmationPage submitAndNavigateToSavingsAccountConfirmationPageWithoutPendingApprovalState(CreateSavingsAccountSubmitParameters formParameters) {
        selenium.type("continuecreatesavingsaccount.input.recommendedAmount",formParameters.getAmount());
        selenium.click("continuecreatesavingsaccount.button.preview");
        waitForPageToLoad();
        selenium.isVisible("approvedButton");
        selenium.click("approvedButton");
        waitForPageToLoad();
        return new CreateSavingsAccountConfirmationPage(selenium);

    }

    public CreateSavingsAccountConfirmationPage submitWithQGAndNavigateToSavingsAccountConfirmationPage(CreateSavingsAccountSubmitParameters formParameters) {
        selenium.type("continuecreatesavingsaccount.input.recommendedAmount",formParameters.getAmount());
        selenium.click("continuecreatesavingsaccount.button.preview");
        waitForPageToLoad();

        // TODO - select some answer
        selenium.click("captureQuestionResponses.button.continue");
        waitForPageToLoad();

        selenium.isVisible("createsavingsaccountpreview.button.submitForApproval");
        selenium.click("createsavingsaccountpreview.button.submitForApproval");
        waitForPageToLoad();
        return new CreateSavingsAccountConfirmationPage(selenium);

    }
}




