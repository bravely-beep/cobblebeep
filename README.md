# beep's Cobblemon Extensions

Extensions to Cobblemon by bravely_beep.

## Documentation

Added Pokémon properties for use with `/pokespawn` and spawn detail config files :

### Respawn

- `respawn_time` allows wild Pokémon knocked out in battle to respawn after a timer.
- `respawn_radius_horizontal` and `respawn_radius_vertical` control the area the respawn can happen in.
- `respawn_deny_caught_species` will prevent a respawn triggering if the nearest player has
  already registered the Pokémon's species as caught in the Pokédex.
  - `respawn_deny_caught_form` will only check if the specific form was registered as caught. 

### Misc

- `hide_label` hides wild Pokémon overhead labels.
- `invulnerable` makes wild Pokémon invulnerable.
- `persistence_required` prevents wild Pokémon despawning.
- `unbattleable` prevents wild Pokémon from being battled.

