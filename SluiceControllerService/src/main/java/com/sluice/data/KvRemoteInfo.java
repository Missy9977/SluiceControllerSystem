package com.sluice.data;

public class KvRemoteInfo {

    private String VarValue;

    private String nVarID;

    private String nVarType;

    private String strVarName;

    public String getVarValue() {
        return VarValue;
    }

    public void setVarValue(String varValue) {
        VarValue = varValue;
    }

    public String getnVarID() {
        return nVarID;
    }

    public void setnVarID(String nVarID) {
        this.nVarID = nVarID;
    }

    public String getnVarType() {
        return nVarType;
    }

    public void setnVarType(String nVarType) {
        this.nVarType = nVarType;
    }

    public String getStrVarName() {
        return strVarName;
    }

    public void setStrVarName(String strVarName) {
        this.strVarName = strVarName;
    }

    @Override
    public String toString() {
        return "KvRemoteInfo{" + "VarValue='" + VarValue + '\'' + ", nVarID='" + nVarID + '\'' + ", nVarType='"
            + nVarType + '\'' + ", strVarName='" + strVarName + '\'' + '}';
    }
}
