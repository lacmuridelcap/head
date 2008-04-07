package org.mifos.framework.struts.tags;

import org.dom4j.DocumentException;
import org.mifos.framework.MifosTestCase;
import static org.mifos.framework.TestUtils.assertWellFormedFragment;

public class MifosFileTagTest extends MifosTestCase{
	public void testrenderInputsForhidden() throws DocumentException{
		MifosFileTag mifosFileTag = new MifosFileTag();
		mifosFileTag.setKeyhm("test1");
		mifosFileTag.setPropertyExpr("test2");
		StringBuffer inputsForhidden=new StringBuffer();
		inputsForhidden.append("<input type=\"hidden\" name=\""+mifosFileTag.getKeyhm()+"\" value=\""+mifosFileTag.getPropertyExpr()+"\" />");
		assertEquals(inputsForhidden.toString(), mifosFileTag.renderInputsForhidden());
		assertWellFormedFragment(mifosFileTag.renderInputsForhidden());
	}
}
