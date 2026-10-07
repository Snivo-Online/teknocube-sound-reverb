# Sound Physics TeknoCube

Fork of [Sound Physics Remastered](https://github.com/henkelmax/sound-physics-remastered) for TeknoCube (Fabric).

- Branch `26.3` (and `master`): Minecraft 26.3. Jar: `release/sound-physics-remastered-fabric-1.5.1+26.3-teknocube.jar`
- Branch `teknocube/record-physics`: Minecraft 26.2. Jar: `release/sound-physics-remastered-fabric-1.5.1+26.2-teknocube.jar`

**Change vs upstream:** `SoundSource.RECORDS` is no longer skipped wholesale. Only `music_disc.*` stay dry when `update_moving_sounds` is off. Plugin speakers, note blocks, and other record-category one-shots get reverb/occlusion.

Music & Sounds menu adds two toggles: master reverb, and reverb for the Jukebox/Speakers slider. Occlusion stays either way. Three live sliders tune speaker/jukebox reverb: bass, body, hall.

Same `mod_id` as upstream (`sound_physics_remastered`) — **replace** the original jar, do not install both.

Client-only. No server / plugin / resource-pack change required.

---

# Sound Physics Remastered

> This is a fork of [Sound Physics Fabric](https://github.com/vlad2305m/Sound-Physics-Fabric) by [vlad2305m](https://github.com/vlad2305m) which is a fork of [Sound Physics](https://github.com/sonicether/Sound-Physics) by [Sonic Ether](https://github.com/sonicether).

A Minecraft mod that provides realistic sound attenuation, reverberation, and absorption through blocks.

Optimized for the use with [Simple Voice Chat](https://www.curseforge.com/minecraft/mc-mods/simple-voice-chat) 2.X.X.


### Requirements
This mod does not require any dependencies, but it is recommended to use the following:

**Fabric**

- [ModMenu](https://modrinth.com/mod/modmenu)
- [ClothConfig](https://modrinth.com/mod/cloth-config)

**Forge**

- [ClothConfig](https://modrinth.com/mod/cloth-config)

---

### Changes to the Original Mod

**Improvements**

- Improved sound processing performance by a factor of 10
- Optimized for [Simple Voice Chat](https://modrinth.com/mod/simple-voice-chat)
- Ported the mod to Forge
- Improved configuration UI
- Made Cloth Config optional
- Tweaked default config values
- Added reflectivity to the config GUI
- Added occlusion variation
- Added block occlusion factor config
- Added debug sound bounce rendering
- Added debug sound occlusion rendering

**Bugfixes**

- Fixed sounds not bouncing more than once
- Fixed sound bouncing using an excessive amount of performance, despite not working at all
- Fixed direction evaluation not taking sound source and primary bounces into account
- Fixed sound allowance not working for `/playsound` command
- Fixed unmapped field names in the reflectivity config
- Fixed sound processing using player position instead of camera position