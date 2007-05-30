
INSERT INTO REPORT(REPORT_ID,REPORT_CATEGORY_ID,REPORT_NAME,REPORT_IDENTIFIER) VALUES(29,6,'Active Loans By Loan Officer','active_loans_by_loan_officer');

INSERT INTO report_jasper_map(REPORT_ID,REPORT_CATEGORY_ID,REPORT_NAME,REPORT_IDENTIFIER, REPORT_JASPER) VALUES 
(29,6,'Active Loans By Loan Officer','active_loans_by_loan_officer',
'ActiveLoansByLoanOfficer.rptdesign');

INSERT INTO LOOKUP_VALUE VALUES(586,87,' ');
INSERT INTO LOOKUP_VALUE_LOCALE VALUES(931,1,586,'Can view Active Loans By Loan Officer');
INSERT INTO ACTIVITY(ACTIVITY_ID,PARENT_ID,ACTIVITY_NAME_LOOKUP_ID,DESCRIPTION_LOOKUP_ID) VALUES(212,150,586,586);
INSERT INTO ROLES_ACTIVITY VALUES (212,1);

update DATABASE_VERSION set DATABASE_VERSION = 120 where DATABASE_VERSION = 119;
