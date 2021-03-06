package act.storage;

import act.app.App;
import act.plugin.Plugin;
import act.util.DestroyableBase;
import org.osgl.bootstrap.Version;
import org.osgl.storage.IStorageService;
import org.osgl.util.C;

import java.util.Map;

/**
 * The base class for Storage Plugin
 */
public abstract class StoragePlugin extends DestroyableBase implements Plugin {

    public static final Version VERSION = Version.of(StoragePlugin.class);

    @Override
    public void register() {
        StoragePluginManager.deplayedRegister(this);
    }

    protected abstract IStorageService initStorageService(String id, App app, Map<String, String> conf);

    protected static Map<String, String> calibrate(Map<String, String> conf, String prefix) {
        Map<String, String> map = C.newMap();
        for (String key : conf.keySet()) {
            String val = conf.get(key);
            if (!key.startsWith("storage")) {
                key = prefix + key;
            }
            map.put(key, val);
        }
        return map;
    }

}
