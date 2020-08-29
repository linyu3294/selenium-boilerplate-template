public class DataTableExample {
    public static void main(String[] args) throws Exception {
        DataTable dataTable = new DataTable("artifacts/input_template.xlsm");
        String tabName = "Sheet1";
        String strRUN_NAME, strEXECUTE, strACTION, strORIGINAL_POLICY, strINQUIRY_TERM, strINQUIRY_DATE, strSOURCE_ENV, strDESTINATION_ENV, strNEW_EFF_DATE, strDRIVERS_VEHICLES_PREFILL, strPULL_DHI, strPULL_CLUE, strEMAIL_ADDRESS, strQUOTE_POLICY_NUMBER, strSTART_TIME, strEND_TIME, strDURATION;

//        textToReadFromCell = dataTable.read(tabName, columnName, rowNumber);

        strRUN_NAME = dataTable.read(tabName, "RUN NAME", 3);
        strEXECUTE = dataTable.read(tabName, "EXECUTE", 3);
        strACTION = dataTable.read(tabName, "ACTION", 3);
        strORIGINAL_POLICY = dataTable.read(tabName, "ORIGINAL POLICY", 3);
        strINQUIRY_TERM = dataTable.read(tabName, "INQUIRY TERM", 3);
        strINQUIRY_DATE = dataTable.read(tabName, "INQUIRY DATE", 3);
        strSOURCE_ENV = dataTable.read(tabName, "SOURCE ENV", 3);
        strDESTINATION_ENV = dataTable.read(tabName, "DESTINATION ENV", 3);
        strNEW_EFF_DATE = dataTable.read(tabName, "NEW EFF DATE", 3);

        System.out.println("Run Name = " + strRUN_NAME);
        System.out.println("Execute = " + strEXECUTE);
        System.out.println("Action = " + strACTION);
        System.out.println("Original Policy = " + strORIGINAL_POLICY);
        System.out.println("Inquiry Term = " + strINQUIRY_TERM);
        System.out.println("Inquiry Date = " + strINQUIRY_DATE);
        System.out.println("Source Environment = " + strSOURCE_ENV);
        System.out.println("Destination Environment = " + strDESTINATION_ENV);
        System.out.println("New Effective Date = " + strNEW_EFF_DATE);

//        textToWriteToCell = "Hi Bob!";
//        dataTable.write(tabName, columnName, rowNumber, textToWriteToCell);

        strSTART_TIME = dataTable.getDate();
        System.out.println("Start Time = " + strSTART_TIME);
        dataTable.write(tabName, "START TIME", 3, strSTART_TIME);
    }
}
