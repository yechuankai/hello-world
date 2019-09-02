package com.wms.common.mybatis.generator.plugins;

import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.config.ColumnRenamingRule;
import org.mybatis.generator.config.DomainObjectRenamingRule;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.internal.util.StringUtility;

import com.itfsw.mybatis.generator.plugins.TableRenameConfigurationPlugin;
import com.itfsw.mybatis.generator.plugins.utils.BeanUtils;

import static org.mybatis.generator.internal.util.JavaBeansUtil.getCamelCaseString;
import static org.mybatis.generator.internal.util.JavaBeansUtil.getValidPropertyName;
import static org.mybatis.generator.internal.util.StringUtility.isTrue;
import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

public class DaoRenameConfigurationPlugin extends TableRenameConfigurationPlugin{

	
	
	public static final String PRO_TABLE_SEARCH_STRING = "domainObjectRenamingRule.searchString";  // 查找 property
    public static final String PRO_TABLE_REPLACE_STRING = "domainObjectRenamingRule.replaceString";  // 替换 property
    public static final String PRO_TABLE_REPLACE_DISABLE = "domainObjectRenamingRule.disable";  // 替换 property
    public static final String PRO_COLUMN_SEARCH_STRING = "columnRenamingRule.searchString";  // 查找 property
    public static final String PRO_COLUMN_REPLACE_STRING = "columnRenamingRule.replaceString";  // 替换 property
    public static final String PRO_COLUMN_REPLACE_DISABLE = "columnRenamingRule.disable";  // 替换 property

    public static final String PRO_CLIENT_PREFIX = "clientPrefix";  // client 开头
    public static final String PRO_EXAMPLE_PREFIX = "examplePrefix"; // example 开头
    public static final String PRO_MODEL_PREFIX = "modelPrefix"; // model 开头
    
    public static final String PRO_CLIENT_SUFFIX = "clientSuffix";  // client 结尾
    public static final String PRO_EXAMPLE_SUFFIX = "exampleSuffix"; // example 结尾
    public static final String PRO_MODEL_SUFFIX = "modelSuffix"; // model 结尾
    

    private String tableSearchString;
    private String tableReplaceString;
    private boolean tableReplaceDisable;
    private String columnSearchString;
    private String columnReplaceString;
    private boolean columnReplaceDisable;


    private String clientPrefix;  // client 开头
    private String examplePrefix; // example 开头
    private String modelPrefix; // model 开头
    
    private String clientSuffix;  // client 结尾
    private String exampleSuffix; // example 结尾
    private String modelSuffix; // model 结尾

    /**
     * {@inheritDoc}
     * @param warnings
     * @return
     */
    @Override
    public boolean validate(List<String> warnings) {
        Properties properties = this.getProperties();

        this.tableSearchString = properties.getProperty(PRO_TABLE_SEARCH_STRING);
        this.tableReplaceString = properties.getProperty(PRO_TABLE_REPLACE_STRING);
        this.columnSearchString = properties.getProperty(PRO_COLUMN_SEARCH_STRING);
        this.columnReplaceString = properties.getProperty(PRO_COLUMN_REPLACE_STRING);  // 和官方行为保持一致

        this.examplePrefix = properties.getProperty(PRO_EXAMPLE_PREFIX);
        this.clientPrefix = properties.getProperty(PRO_CLIENT_PREFIX);
        this.modelPrefix = properties.getProperty(PRO_MODEL_PREFIX);
        
        this.examplePrefix = this.examplePrefix == null ? "" : this.examplePrefix;
        this.clientPrefix = this.clientPrefix == null ? "" : this.clientPrefix;
        this.modelPrefix = this.modelPrefix == null ? "" : this.modelPrefix;
        
        this.exampleSuffix = properties.getProperty(PRO_EXAMPLE_SUFFIX);
        this.clientSuffix = properties.getProperty(PRO_CLIENT_SUFFIX);
        this.modelSuffix = properties.getProperty(PRO_MODEL_SUFFIX);
        
        this.exampleSuffix = this.exampleSuffix == null ? "" : this.exampleSuffix;
        this.clientSuffix = this.clientSuffix == null ? "" : this.clientSuffix;
        this.modelSuffix = this.modelSuffix == null ? "" : this.modelSuffix;

        return super.validate(warnings);
    }

    /**
     * 表配置
     * @param introspectedTable
     */
    @Override
    public void tableConfiguration(IntrospectedTable introspectedTable) {
        try {
            TableConfiguration tableConfiguration = introspectedTable.getTableConfiguration();
            FullyQualifiedTable fullyQualifiedTable = introspectedTable.getFullyQualifiedTable();

            String tableReplaceDisable = tableConfiguration.getProperty(PRO_TABLE_REPLACE_DISABLE);
            this.tableReplaceDisable = tableReplaceDisable == null ? false : StringUtility.isTrue(tableReplaceDisable);
            String columnReplaceDisable = tableConfiguration.getProperty(PRO_COLUMN_REPLACE_DISABLE);
            this.columnReplaceDisable = columnReplaceDisable == null ? false : StringUtility.isTrue(columnReplaceDisable);

            String javaClientInterfacePackage = (String) BeanUtils.invoke(introspectedTable, IntrospectedTable.class, "calculateJavaClientInterfacePackage");
            String sqlMapPackage = (String) BeanUtils.invoke(introspectedTable, IntrospectedTable.class, "calculateSqlMapPackage");
            String javaModelPackage = (String) BeanUtils.invoke(introspectedTable, IntrospectedTable.class, "calculateJavaModelPackage");
            // --------------------- table 重命名 ----------------------------
            if (tableConfiguration.getDomainObjectRenamingRule() == null
                    && this.tableSearchString != null && !this.tableReplaceDisable) {
                DomainObjectRenamingRule rule = new DomainObjectRenamingRule();
                rule.setSearchString(this.tableSearchString);
                rule.setReplaceString(this.tableReplaceString);
                tableConfiguration.setDomainObjectRenamingRule(rule);
                BeanUtils.setProperty(introspectedTable.getFullyQualifiedTable(), "domainObjectRenamingRule", rule);

                // 重新初始化一下属性
                BeanUtils.invoke(introspectedTable, IntrospectedTable.class, "calculateJavaClientAttributes");
                BeanUtils.invoke(introspectedTable, IntrospectedTable.class, "calculateModelAttributes");
                BeanUtils.invoke(introspectedTable, IntrospectedTable.class, "calculateXmlAttributes");
            }
            // TODO 官方bug临时修复
            if (tableConfiguration.getDomainObjectRenamingRule() != null) {
                String domainObjectName = fullyQualifiedTable.getDomainObjectName();
                // 首字母大写
                BeanUtils.setProperty(fullyQualifiedTable, "domainObjectName", getCamelCaseString(domainObjectName, true));

                // 重新初始化一下属性
                BeanUtils.invoke(introspectedTable, IntrospectedTable.class, "calculateJavaClientAttributes");
                BeanUtils.invoke(introspectedTable, IntrospectedTable.class, "calculateModelAttributes");
                BeanUtils.invoke(introspectedTable, IntrospectedTable.class, "calculateXmlAttributes");
            }
            // --------------------- column 重命名 ---------------------------
            if (tableConfiguration.getColumnRenamingRule() == null
                    && this.columnSearchString != null && !this.columnReplaceDisable) {
                ColumnRenamingRule rule = new ColumnRenamingRule();
                rule.setSearchString(this.columnSearchString);
                rule.setReplaceString(this.columnReplaceString);
                tableConfiguration.setColumnRenamingRule(rule);

                // introspectedTable 使用到的所有column过滤并替换
                this.renameColumns(introspectedTable.getAllColumns(), rule, tableConfiguration);
            }

            // ---------------------- 后缀修正 -------------------------------
            // 1. client
            // mapper
            StringBuilder sb = new StringBuilder();
            sb.append(javaClientInterfacePackage);
            sb.append('.');
            if (stringHasValue(tableConfiguration.getMapperName())) {
                sb.append(tableConfiguration.getMapperName());
            } else {
                if (stringHasValue(fullyQualifiedTable.getDomainObjectSubPackage())) {
                    sb.append(fullyQualifiedTable.getDomainObjectSubPackage());
                    sb.append('.');
                }
                sb.append(this.clientPrefix);
                sb.append(fullyQualifiedTable.getDomainObjectName());
                sb.append(this.clientSuffix);
            }
            introspectedTable.setMyBatis3JavaMapperType(sb.toString());
            // xml mapper namespace
            sb.setLength(0);
            sb.append(sqlMapPackage);
            sb.append('.');
            sb.append(this.clientPrefix);
            if (stringHasValue(tableConfiguration.getMapperName())) {
                sb.append(tableConfiguration.getMapperName());
            } else {
                sb.append(fullyQualifiedTable.getDomainObjectName());
                sb.append(this.clientSuffix);
            }
            introspectedTable.setMyBatis3FallbackSqlMapNamespace(sb.toString());
            // xml file
            sb.setLength(0);
            sb.append(this.clientPrefix);
            sb.append(fullyQualifiedTable.getDomainObjectName());
            sb.append(this.clientSuffix);
            sb.append(".xml");

            introspectedTable.setMyBatis3XmlMapperFileName(sb.toString());
            
            // 2. example
            
            sb = new StringBuilder();
            sb.append(javaModelPackage);
            sb.append('.');
            sb.append(this.examplePrefix);
            sb.append(fullyQualifiedTable.getDomainObjectName());
            sb.append(this.exampleSuffix);
            introspectedTable.setExampleType(sb.toString());
            
            // 3. model
            
            sb = new StringBuilder();
            sb.append(javaModelPackage);
            sb.append('.');
            sb.append(this.modelPrefix);
            sb.append(fullyQualifiedTable.getDomainObjectName());
            sb.append("Key");
            sb.append(this.modelSuffix);
            introspectedTable.setPrimaryKeyType(sb.toString());

            sb.setLength(0);
            sb.append(javaModelPackage);
            sb.append('.');
            sb.append(this.modelPrefix);
            sb.append(fullyQualifiedTable.getDomainObjectName());
            sb.append(this.modelSuffix);
            introspectedTable.setBaseRecordType(sb.toString());

            sb.setLength(0);
            sb.append(javaModelPackage);
            sb.append('.');
            sb.append(this.modelPrefix);
            sb.append(fullyQualifiedTable.getDomainObjectName());
            sb.append("WithBLOBs");
            sb.append(this.modelSuffix);
            introspectedTable.setRecordWithBLOBsType(sb.toString());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * column rename
     * @param columns
     * @param rule
     * @param tc
     */
    private void renameColumns(List<IntrospectedColumn> columns, ColumnRenamingRule rule, TableConfiguration tc) {
        Pattern pattern = Pattern.compile(rule.getSearchString());
        String replaceString = rule.getReplaceString();
        replaceString = replaceString == null ? "" : replaceString;

        for (IntrospectedColumn introspectedColumn : columns) {
            String calculatedColumnName;
            if (pattern == null) {
                calculatedColumnName = introspectedColumn.getActualColumnName();
            } else {
                Matcher matcher = pattern.matcher(introspectedColumn.getActualColumnName());
                calculatedColumnName = matcher.replaceAll(replaceString);
            }

            if (isTrue(tc.getProperty(PropertyRegistry.TABLE_USE_ACTUAL_COLUMN_NAMES))) {
                introspectedColumn.setJavaProperty(getValidPropertyName(calculatedColumnName));
            } else if (isTrue(tc.getProperty(PropertyRegistry.TABLE_USE_COMPOUND_PROPERTY_NAMES))) {
                StringBuilder sb = new StringBuilder();
                sb.append(calculatedColumnName);
                sb.append('_');
                sb.append(getCamelCaseString(introspectedColumn.getRemarks(), true));
                introspectedColumn.setJavaProperty(getValidPropertyName(sb.toString()));
            } else {
                introspectedColumn.setJavaProperty(getCamelCaseString(calculatedColumnName, false));
            }
        }
    }
	
}
