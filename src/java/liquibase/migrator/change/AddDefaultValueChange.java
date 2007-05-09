package liquibase.migrator.change;

import liquibase.database.MSSQLDatabase;
import liquibase.database.MySQLDatabase;
import liquibase.database.OracleDatabase;
import liquibase.database.PostgresDatabase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class AddDefaultValueChange extends AbstractChange {
    private String tableName;
    private String columnName;
    private String defaultValue;

    public AddDefaultValueChange() {
        super("addDefaultValue", "Add Default Value");
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String[] generateStatements(MSSQLDatabase database) {
        return new String[] {
                "ALTER TABLE " + getTableName() + " WITH NOCHECK ADD CONSTRAINT "+getColumnName()+"DefaultValue DEFAULT '"+getDefaultValue()+"' FOR " + getColumnName(),
        };
    }

    public String[] generateStatements(MySQLDatabase database) {
        return new String[] {
                "ALTER TABLE " + getTableName() + " ALTER " + getColumnName() + " SET DEFAULT '"+getDefaultValue()+"'",
        };
    }

    public String[] generateStatements(OracleDatabase database) {
        return new String[] {
            "ALTER TABLE " + getTableName() + " MODIFY " + getColumnName() + " DEFAULT '"+getDefaultValue()+"'",
        };
    }

    public String[] generateStatements(PostgresDatabase database) {
        return new String[] {
                "ALTER TABLE " + getTableName() + " ALTER COLUMN  " + getColumnName() + " SET DEFAULT '"+getDefaultValue()+"'",
        };
    }

    protected AbstractChange[] createInverses() {
        DropDefaultValueChange inverse = new DropDefaultValueChange();
        inverse.setTableName(getTableName());
        inverse.setColumnName(getColumnName());

        return new AbstractChange[] {
                inverse
        };
    }

    public String getConfirmationMessage() {
        return "Default Value Added";
    }

    public Element createNode(Document currentMigrationFileDOM) {
        Element node = currentMigrationFileDOM.createElement(getTagName());
        node.setAttribute("tableName", getTableName());
        node.setAttribute("columnName", getColumnName());
        node.setAttribute("defaultValue", getDefaultValue());

        return node;
    }
}
