package com.sonicether.soundphysics.config;

import com.sonicether.soundphysics.Loggers;
import com.sonicether.soundphysics.SoundPhysics;

import de.maxhenkel.configbuilder.ConfigBuilder;
import de.maxhenkel.configbuilder.entry.ConfigEntry;
import net.minecraft.sounds.SoundSource;

public class SoundPhysicsConfig {

    public final ConfigEntry<Boolean> enabled;
    public final ConfigEntry<Boolean> reverbEnabled;
    public final ConfigEntry<Boolean> recordReverb;
    public final ConfigEntry<Boolean> physicsMaster;
    public final ConfigEntry<Boolean> physicsMusic;
    public final ConfigEntry<Boolean> physicsRecords;
    public final ConfigEntry<Boolean> physicsWeather;
    public final ConfigEntry<Boolean> physicsBlocks;
    public final ConfigEntry<Boolean> physicsHostile;
    public final ConfigEntry<Boolean> physicsNeutral;
    public final ConfigEntry<Boolean> physicsPlayers;
    public final ConfigEntry<Boolean> physicsAmbient;
    public final ConfigEntry<Boolean> physicsVoice;
    public final ConfigEntry<Boolean> physicsUi;
    public final ConfigEntry<Float> recordReverbBass;
    public final ConfigEntry<Float> recordReverbBody;
    public final ConfigEntry<Float> recordLateSend;
    public final ConfigEntry<Float> recordGainLf;
    public final ConfigEntry<Float> recordDecayLfRatio;
    public final ConfigEntry<Float> recordFacingReverb;
    public final ConfigEntry<Float> recordFacingImpact;

    public final ConfigEntry<Float> attenuationFactor;
    public final ConfigEntry<Float> reverbAttenuationDistance;
    public final ConfigEntry<Float> reverbGain;
    public final ConfigEntry<Float> reverbBrightness;
    public final ConfigEntry<Float> reverbDistance;
    public final ConfigEntry<Float> blockAbsorption;
    public final ConfigEntry<Float> occlusionVariation;
    public final ConfigEntry<Float> defaultBlockReflectivity;
    public final ConfigEntry<Float> defaultBlockOcclusionFactor;
    public final ConfigEntry<Float> soundDistanceAllowance;
    public final ConfigEntry<Float> airAbsorption;
    public final ConfigEntry<Float> underwaterFilter;
    public final ConfigEntry<Boolean> evaluateAmbientSounds;

    public final ConfigEntry<Integer> environmentEvaluationRayCount;
    public final ConfigEntry<Integer> environmentEvaluationRayBounces;
    public final ConfigEntry<Float> nonFullBlockOcclusionFactor;
    public final ConfigEntry<Integer> maxOcclusionRays;
    public final ConfigEntry<Float> maxOcclusion;
    public final ConfigEntry<Boolean> strictOcclusion;
    public final ConfigEntry<Boolean> soundDirectionEvaluation;
    public final ConfigEntry<Boolean> redirectNonOccludedSounds;
    public final ConfigEntry<Boolean> updateMovingSounds;
    public final ConfigEntry<Integer> soundUpdateInterval;
    public final ConfigEntry<Double> maxSoundProcessingDistance;
    public final ConfigEntry<Boolean> unsafeLevelAccess;
    public final ConfigEntry<Integer> levelCloneRange;
    public final ConfigEntry<Integer> levelCloneMaxRetainTicks;
    public final ConfigEntry<Integer> levelCloneMaxRetainBlockDistance;

    public final ConfigEntry<Boolean> debugLogging;
    public final ConfigEntry<Boolean> occlusionLogging;
    public final ConfigEntry<Boolean> environmentLogging;
    public final ConfigEntry<Boolean> performanceLogging;
    public final ConfigEntry<Boolean> renderSoundBounces;
    public final ConfigEntry<Boolean> renderOcclusion;

    public final ConfigEntry<Boolean> simpleVoiceChatIntegration;
    public final ConfigEntry<Boolean> hearSelf;

    public SoundPhysicsConfig(ConfigBuilder builder) {
        enabled = builder.booleanEntry("enabled", true)
                .comment("Enables/Disables all sound effects");
        reverbEnabled = builder.booleanEntry("reverb_enabled", true)
                .comment("Enables/Disables reverberation. Occlusion and attenuation stay active.");
        recordReverb = builder.booleanEntry("record_reverb", true)
                .comment(
                        "Enables/Disables reverberation for the Jukebox/Note Blocks category",
                        "This includes plugin speakers. Occlusion stays active."
                );
        physicsMaster = physicsCategory(builder, "master", false);
        physicsMusic = physicsCategory(builder, "music", false);
        physicsRecords = physicsCategory(builder, "record", true);
        physicsWeather = physicsCategory(builder, "weather", false);
        physicsBlocks = physicsCategory(builder, "block", false);
        physicsHostile = physicsCategory(builder, "hostile", false);
        physicsNeutral = physicsCategory(builder, "neutral", false);
        physicsPlayers = physicsCategory(builder, "player", false);
        physicsAmbient = physicsCategory(builder, "ambient", false);
        physicsVoice = physicsCategory(builder, "voice", false);
        physicsUi = physicsCategory(builder, "ui", false);
        recordReverbBass = builder.floatEntry("record_reverb_bass", 0.7F, 0F, 1F)
                .comment(
                        "TeknoCube music: how dark the speaker/jukebox reverb is",
                        "0 = bright SPR default, 1 = boomier low end"
                );
        recordReverbBody = builder.floatEntry("record_reverb_body", 1.4F, 0.5F, 2.5F)
                .comment(
                        "TeknoCube music: early/mid room send multiplier for speakers/jukebox"
                );
        recordLateSend = builder.floatEntry("record_late_send", 2.0F, 0.5F, 4F)
                .comment(
                        "TeknoCube music: hall/late reverb send multiplier for speakers/jukebox"
                );
        recordGainLf = builder.floatEntry("record_gain_lf", 1.0F, 0F, 1F)
                .comment(
                        "EAX GAINLF: low-frequency reverb gain (global effect slots)",
                        "1.0 is max. Footsteps share these slots."
                );
        recordDecayLfRatio = builder.floatEntry("record_decay_lf_ratio", 1.35F, 0.1F, 2F)
                .comment(
                        "EAX DECAY_LFRATIO: how much longer bass rings vs treble (global)",
                        "1.0 = same decay, 1.5 = bass tail 50% longer"
                );
        recordFacingReverb = builder.floatEntry("record_facing_reverb", 1.0F, 0F, 2F)
                .comment(
                        "Extra wet send when looking at speakers (does not muffle the dry hit)",
                        "0 = no facing boost, 1 = double wet when facing the source"
                );
        recordFacingImpact = builder.floatEntry("record_facing_impact", 1.0F, 0F, 2F)
                .comment(
                        "On-axis speaker punch: louder dry, boomier early reflections, proximity 2-8 blocks",
                        "0 = off, 1 = default recipe, 2 = double. Does not muffle the dry hit."
                );

        attenuationFactor = builder
                .floatEntry("attenuation_factor", 1F, 0.1F, 1F)
                .comment(
                        "Affects how quiet a sound gets based on distance",
                        "Lower values mean distant sounds are louder",
                        "This setting requires you to be in singleplayer or having the mod installed on the server",
                        "1.0 is the physically correct value"
                );
        reverbAttenuationDistance = builder
                .floatEntry("reverb_attenuation_distance", 0F, 0F, 512F)
                .comment(
                        "The ray distance at which reverb starts",
                        "0.0 disables reverb attenuation"
                );
        reverbGain = builder
                .floatEntry("reverb_gain", 1F, 0.1F, 2F)
                .comment("The volume of simulated reverberations");
        reverbBrightness = builder.floatEntry("reverb_brightness", 1F, 0.1F, 2F)
                .comment(
                        "The brightness of reverberation",
                        "Higher values result in more high frequencies in reverberation",
                        "Lower values give a more muffled sound to the reverb"
                );
        reverbDistance = builder
                .floatEntry("reverb_distance", 1.5F, 0.1F, 16F)
                .comment("The distance of reverb relative to the sound distance");
        blockAbsorption = builder.floatEntry("block_absorption", 1F, 0.1F, 4F)
                .comment("The amount of sound that will be absorbed when traveling through blocks");
        occlusionVariation = builder.floatEntry("occlusion_variation", 0.35F, 0F, 16F)
                .comment("Higher values mean smaller objects won't be considered as occluding");
        defaultBlockReflectivity = builder.floatEntry("default_block_reflectivity", 0.5F, 0.1F, 4F)
                .comment(
                        "The default amount of sound reflectance energy for all blocks",
                        "Lower values result in more conservative reverb simulation with shorter reverb tails",
                        "Higher values result in more generous reverb simulation with higher reverb tails"
                );
        defaultBlockOcclusionFactor = builder.floatEntry("default_block_occlusion_factor", 1F, 0F, 10F)
                .comment(
                        "The default amount of occlusion for all blocks",
                        "Lower values will result in sounds being less muffled through walls",
                        "Higher values mean sounds will be not audible though thicker walls"
                );
        soundDistanceAllowance = builder.floatEntry("sound_distance_allowance", 4F, 1F, 6F)
                .comment(
                        "Minecraft won't allow sounds to play past a certain distance",
                        "This parameter is a multiplier for how far away a sound source is allowed to be in order for it to actually play",
                        "This setting only takes affect in singleplayer worlds and when installed on the server"
                );
        airAbsorption = builder.floatEntry("air_absorption", 1F, 0F, 5F)
                .comment(
                        "A value controlling the amount that air absorbs high frequencies with distance",
                        "A value of 1.0 is physically correct for air with normal humidity and temperature",
                        "Higher values mean air will absorb more high frequencies with distance",
                        "0 disables this effect"
                );
        underwaterFilter = builder.floatEntry("underwater_filter", 0.9F, 0F, 1F)
                .comment(
                        "How much sound is filtered when the player is underwater",
                        "0.0 means no filter",
                        "1.0 means fully filtered"
                );
        evaluateAmbientSounds = builder.booleanEntry("evaluate_ambient_sounds", false)
                .comment(
                        "Whether sounds like cave, nether or underwater ambient sounds should have sound physics"
                );

        environmentEvaluationRayCount = builder.integerEntry("environment_evaluation_ray_count", 32, 8, 64)
                .comment(
                        "The number of rays to trace to determine reverberation for each sound source",
                        "More rays provides more consistent tracing results but takes more time to calculate",
                        "Decrease this value if you experience lag spikes when sounds play"
                );
        environmentEvaluationRayBounces = builder.integerEntry("environment_evaluation_ray_bounces", 4, 2, 64)
                .comment(
                        "The number of rays bounces to trace to determine reverberation for each sound source",
                        "More bounces provides more echo and sound ducting but takes more time to calculate",
                        "Decrease this value if you experience lag spikes when sounds play"
                );
        nonFullBlockOcclusionFactor = builder.floatEntry("non_full_block_occlusion_factor", 0.25F, 0F, 1F)
                .comment("If sound hits a non-full-square side, block occlusion is multiplied by this");
        maxOcclusionRays = builder.integerEntry("max_occlusion_rays", 16, 1, 128)
                .comment(
                        "The maximum amount of rays to determine occlusion",
                        "Directly correlates to the amount of blocks between walls that are considered"
                );
        maxOcclusion = builder.floatEntry("max_occlusion", 64F, 0F, 1024F)
                .comment("The amount at which occlusion is capped");
        strictOcclusion = builder.booleanEntry("strict_occlusion", false)
                .comment("If enabled, the occlusion calculation only uses one path between the sound source and the listener instead of 9");
        soundDirectionEvaluation = builder.booleanEntry("sound_direction_evaluation", true)
                .comment("Whether to try calculating where the sound should come from based on reflections");
        redirectNonOccludedSounds = builder.booleanEntry("redirect_non_occluded_sounds", true)
                .comment("Skip redirecting non-occluded sounds (the ones you can see directly)");
        updateMovingSounds = builder.booleanEntry("update_moving_sounds", false)
                .comment(
                        "If music discs or other longer sounds should be frequently reevaluated",
                        "TeknoCube: only music_disc.* skip physics when this is false. Other RECORDS sounds (note blocks, plugin speakers) still get reverb"
                );
        soundUpdateInterval = builder.integerEntry("sound_update_interval", 5, 1, Integer.MAX_VALUE)
                .comment(
                        "The interval in ticks that moving sounds are reevaluated",
                        "Lower values mean more frequent reevaluation but also more lag",
                        "This option only takes effect if update_moving_sounds is enabled"
                );
        maxSoundProcessingDistance = builder.doubleEntry("max_sound_processing_distance", 512D, 0D, Double.MAX_VALUE)
                .comment(
                        "The maximum distance a sound can be processed"
                );
        unsafeLevelAccess = builder.booleanEntry("unsafe_level_access", false)
                .comment(
                        "Disable level clone and cache. This will fall back to original main thread access.",
                        "WARNING! Enabling this will cause instability and issues with other mods."
                );
        levelCloneRange = builder.integerEntry("level_clone_range", 4, 2, 16)
                .comment("The radius of chunks to clone for level access");
        levelCloneMaxRetainTicks = builder.integerEntry("level_clone_max_retain_ticks", 20, 1, Integer.MAX_VALUE,
                "The maximum number of ticks to retain the cloned level in the cache"
        );
        levelCloneMaxRetainBlockDistance = builder.integerEntry("level_clone_max_retain_block_distance", 16, 1, Integer.MAX_VALUE,
                "The maximum distance a player can move from the cloned origin before invalidation"
        );


        debugLogging = builder.booleanEntry("debug_logging", false)
                .comment("Enables debug logging");
        occlusionLogging = builder.booleanEntry("occlusion_logging", false)
                .comment("Provides more information about occlusion in the logs");
        environmentLogging = builder.booleanEntry("environment_logging", false)
                .comment("Provides more information about the environment calculation in the logs");
        performanceLogging = builder.booleanEntry("performance_logging", false)
                .comment("Provides more information about how long computations take");
        renderSoundBounces = builder.booleanEntry("render_sound_bounces", false)
                .comment("If enabled, the path of the sound will be rendered in game");
        renderOcclusion = builder.booleanEntry("render_occlusion", false)
                .comment("If enabled, occlusion will be visualized in game");

        simpleVoiceChatIntegration = builder.booleanEntry("simple_voice_chat_integration", true)
                .comment("Enables/Disables sound effects for Simple Voice Chat audio");
        hearSelf = builder.booleanEntry("simple_voice_chat_hear_self", false)
                .comment("Enables/Disables hearing your own echo with Simple Voice Chat");
    }

    public void reloadClient() {
        Loggers.log("Reloading reverb parameters");
        SoundPhysics.syncReverbParams();
    }

    public boolean isPhysicsCategoryEnabled(SoundSource category) {
        return physicsFor(category).get();
    }

    public ConfigEntry<Boolean> physicsFor(SoundSource category) {
        return switch (category) {
            case MASTER -> physicsMaster;
            case MUSIC -> physicsMusic;
            case RECORDS -> physicsRecords;
            case WEATHER -> physicsWeather;
            case BLOCKS -> physicsBlocks;
            case HOSTILE -> physicsHostile;
            case NEUTRAL -> physicsNeutral;
            case PLAYERS -> physicsPlayers;
            case AMBIENT -> physicsAmbient;
            case VOICE -> physicsVoice;
            case UI -> physicsUi;
        };
    }

    public static SoundSource[] physicsCategoryOrder() {
        return new SoundSource[]{
                SoundSource.RECORDS,
                SoundSource.MASTER,
                SoundSource.MUSIC,
                SoundSource.WEATHER,
                SoundSource.BLOCKS,
                SoundSource.HOSTILE,
                SoundSource.NEUTRAL,
                SoundSource.PLAYERS,
                SoundSource.AMBIENT,
                SoundSource.VOICE,
                SoundSource.UI
        };
    }

    private static ConfigEntry<Boolean> physicsCategory(ConfigBuilder builder, String name, boolean defaultValue) {
        return builder.booleanEntry("physics_category_" + name, defaultValue)
                .comment(
                        "TeknoCube: run raycast/occlusion/reverb for sound category '" + name + "'",
                        "Default off except jukebox/note blocks (record)"
                );
    }

}
