package com.cn.pojo;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.List;

@Alias("ConsultContract")
public class ConsultContract implements Serializable {
    
    private static final long serialVersionUID = 8127035730921338189L;
    
    private Integer contractId;
    
    private String psptId;
    
    private String contractCode;
    
    private String activeTime;
    
    private Integer state;

    private List<ConsultIdCardInfo> infos;

    public List<ConsultIdCardInfo> getInfos() {
        return infos;
    }

    public void setInfos(List<ConsultIdCardInfo> infos) {
        this.infos = infos;
    }

    public Integer getContractId() {
        return contractId;
    }
    
    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }
    
    public String getPsptId() {
        return psptId;
    }
    
    public void setPsptId(String psptId) {
        this.psptId = psptId;
    }
    
    public String getContractCode() {
        return contractCode;
    }
    
    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }
    
    public String getActiveTime() {
        return activeTime;
    }
    
    public void setActiveTime(String activeTime) {
        this.activeTime = activeTime;
    }
    
    public Integer getState() {
        return state;
    }
    
    public void setState(Integer state) {
        this.state = state;
    }
}
