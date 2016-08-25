package com.github.jmetzz.laboratory.xml_processing.sax.complexExample;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public abstract class Account implements Serializable {

   /* static final long serialVersionUID = 7968446023634800013L;
    String m_id;
    String m_name;
    String m_description;
    boolean m_isActive;

    private Map<String, AccountConfig> m_configs = new HashMap<String, AccountConfig>();


    ClearingSystem m_clearingSystem;

    public Account() {
    }


    public Account(String p_id, String p_name, String p_description, boolean p_isActive, ClearingSystem p_clearingSystem) {
        m_id = p_id;
        m_name = p_name;
        m_description = p_description;
        m_isActive = p_isActive;
        m_clearingSystem = p_clearingSystem;
    }

    *//**
     * Returns the clearingSystem.
     *
     * @return ClearingSystem
     *//*
    public ClearingSystem getClearingSystem() {
        return m_clearingSystem;
    }

    *//**
     * Returns the id.
     *
     * @return String
     *//*
    public String getId() {
        return m_id;
    }

    *//**
     * Sets the clearingSystem.
     *
     * @param clearingSystem The clearingSystem to set
     *//*
    public void setClearingSystem(ClearingSystem p_clearingSystem) {
        m_clearingSystem = p_clearingSystem;
    }

    *//**
     * Sets the id.
     *
     * @param id The id to set
     *//*
    public void setId(String p_id) {
        m_id = p_id;
    }

    *//**
     * @return
     *//*
    public String getDescription() {
        return m_description;
    }

    *//**
     * @return
     *//*
    public String getName() {
        return m_name;
    }

    *//**
     * @param p_string
     *//*
    public void setDescription(String p_string) {
        m_description = p_string;
    }

    *//**
     * @param p_string
     *//*
    public void setName(String p_string) {
        m_name = p_string;
    }

    *//**
     * @return
     *//*
    public boolean isActive() {
        return m_isActive;
    }

    *//**
     * @param p_isActive
     *//*
    public void setActive(boolean p_isActive) {
        m_isActive = p_isActive;
    }

    *//**
     * @param p_isActive
     *//*
    public void setActive(String p_isActive) {
        if (p_isActive != null) {
            m_isActive = Boolean.parseBoolean(p_isActive);
        }
    }

    public AccountConfig getConfiguration(String p_configId) {
        return m_configs.get(p_configId);
    }


    public void addConfiguration(AccountConfig p_config) {
        if (p_config != null) {
            m_configs.put(p_config.getId(), p_config);
        }
    }

    *//**
     * @return String representation of the Account instance
     * @author exb165
     *//*
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer("Account[");
        buffer.append("id=" + getId());
        buffer.append(",name=" + getName());
        buffer.append(",description=" + getDescription());
        buffer.append(",clearingSystem=" + getClearingSystem());
        buffer.append("]");

        return buffer.toString();
    }*/

}
