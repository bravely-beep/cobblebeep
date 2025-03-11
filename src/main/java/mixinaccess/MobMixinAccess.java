package mixinaccess;

import com.bravelybeep.cobblebeep.entity.BeepMob;

public interface MobMixinAccess extends BeepMob {
    boolean cobbleBeep$getPersistenceRequired();
    void cobbleBeep$setPersistenceRequired(boolean value);

    default boolean getPersistenceRequired() {
        return cobbleBeep$getPersistenceRequired();
    }
    default void setPersistenceRequired(boolean value) {
        cobbleBeep$setPersistenceRequired(value);
    }
}
