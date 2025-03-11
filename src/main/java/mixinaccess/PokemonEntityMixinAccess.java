package mixinaccess;

import com.bravelybeep.cobblebeep.entity.BeepPokemonEntity;
import com.bravelybeep.cobblebeep.PokeSpawnerData;
import org.jetbrains.annotations.NotNull;

public interface PokemonEntityMixinAccess extends BeepPokemonEntity {
    @NotNull PokeSpawnerData cobbleBeep$getRespawnData();
    void cobbleBeep$setRespawnData(@NotNull PokeSpawnerData value);

    default @NotNull PokeSpawnerData getRespawnData() {
        return cobbleBeep$getRespawnData();
    }
    default void setRespawnData(@NotNull PokeSpawnerData value) {
        cobbleBeep$setRespawnData(value);
    }
}
