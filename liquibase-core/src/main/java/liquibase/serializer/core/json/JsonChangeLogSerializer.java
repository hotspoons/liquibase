package liquibase.serializer.core.json;

import liquibase.serializer.LiquibaseSerializable;
import liquibase.serializer.core.yaml.YamlChangeLogSerializer;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Represent;

public class JsonChangeLogSerializer extends YamlChangeLogSerializer {

    @Override
    public String[] getValidFileExtensions() {
        return new String[]{
                "json"
        };
    }

}
