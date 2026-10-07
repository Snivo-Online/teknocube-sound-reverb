package com.sonicether.soundphysics.config;

import com.sonicether.soundphysics.SoundPhysicsMod;

public class ReverbParams {
    public float decayTime;                 // min: 0.1F 	max: 10.0F
    public float density;                   // min: 0.0F 	max: 1.0F
    public float diffusion;                 // min: 0.0F 	max: 1.0F
    public float gain;                      // min: 0.0F 	max: 1.0F
    public float gainHF;                    // min: 0.0F 	max: 1.0F
    public float gainLF;                    // min: 0.0F 	max: 1.0F
    public float decayHFRatio;              // min: 0.1F 	max: 2.0F
    public float decayLFRatio;              // min: 0.1F 	max: 2.0F
    public float lfReference;               // min: 20.0F max: 1000.0F
    public float reflectionsGain;           // min: 0.1F 	max: 3.16F
    public float reflectionsDelay;          // min: 0.0F 	max: 0.3F
    public float lateReverbGain;            // min: 0.0F 	max: 10.0F
    public float lateReverbDelay;           // min: 0.0F 	max: 0.1F
    public float airAbsorptionGainHF;       // min: 0.892F 	max: 1.0F
    public float roomRolloffFactor;         // min: 0.0F	max: 10.0F

    public static ReverbParams getReverb0() {
        ReverbParams r = new ReverbParams();
        r.decayTime = 0.22F;
        r.density = 0.4F;
        r.diffusion = 1F;
        r.gain = 0.2F * globalReverbMultiplier() * 0.85F;
        r.gainHF = 0.85F;
        r.gainLF = gainLf();
        r.decayHFRatio = 0.6F * SoundPhysicsMod.CONFIG.reverbBrightness.get();
        r.decayLFRatio = decayLfRatio();
        r.lfReference = 180F;
        r.reflectionsGain = 2.5F;
        r.reflectionsDelay = 0.001F;
        r.lateReverbGain = 1.26F;
        r.lateReverbDelay = 0.011F;
        r.airAbsorptionGainHF = 0.994F;
        r.roomRolloffFactor = 0.16F * SoundPhysicsMod.CONFIG.attenuationFactor.get();

        return r;
    }

    public static ReverbParams getReverb1() {
        ReverbParams r = new ReverbParams();
        r.decayTime = 0.55F;
        r.density = 0.35F;
        r.diffusion = 1F;
        r.gain = 0.3f * globalReverbMultiplier() * 0.85F;
        r.gainHF = 0.85F;
        r.gainLF = gainLf();
        r.decayHFRatio = 0.7F * SoundPhysicsMod.CONFIG.reverbBrightness.get();
        r.decayLFRatio = decayLfRatio();
        r.lfReference = 180F;
        r.reflectionsGain = 0.2F;
        r.reflectionsDelay = 0.015F;
        r.lateReverbGain = 1.26F;
        r.lateReverbDelay = 0.011F;
        r.airAbsorptionGainHF = 0.994F;
        r.roomRolloffFactor = 0.15F * SoundPhysicsMod.CONFIG.attenuationFactor.get();

        return r;
    }

    public static ReverbParams getReverb2() {
        ReverbParams r = new ReverbParams();
        r.decayTime = 1.68F;
        r.density = 0.25F;
        r.diffusion = 1F;
        r.gain = 0.5F * globalReverbMultiplier() * 0.85F;
        r.gainHF = 0.8F;
        r.gainLF = gainLf();
        r.decayHFRatio = 0.7F * SoundPhysicsMod.CONFIG.reverbBrightness.get();
        r.decayLFRatio = decayLfRatio();
        r.lfReference = 180F;
        r.reflectionsGain = 0.F;
        r.reflectionsDelay = 0.021F;
        r.lateReverbGain = 1.4F;
        r.lateReverbDelay = 0.021F;
        r.airAbsorptionGainHF = 0.994F;
        r.roomRolloffFactor = 0.13F * SoundPhysicsMod.CONFIG.attenuationFactor.get();

        return r;
    }

    public static ReverbParams getReverb3() {
        ReverbParams r = new ReverbParams();
        r.decayTime = 4.142F;
        r.density = 0.55F;
        r.diffusion = 1F;
        r.gain = 0.4f * globalReverbMultiplier() * 0.85F;
        r.gainHF = 0.75F;
        r.gainLF = gainLf();
        r.decayHFRatio = 0.7F * SoundPhysicsMod.CONFIG.reverbBrightness.get();
        r.decayLFRatio = decayLfRatio();
        r.lfReference = 180F;
        r.reflectionsGain = 0F;
        r.reflectionsDelay = 0.025F;
        r.lateReverbGain = 1.45F;
        r.lateReverbDelay = 0.021F;
        r.airAbsorptionGainHF = 0.994F;
        r.roomRolloffFactor = 0.11F * SoundPhysicsMod.CONFIG.attenuationFactor.get();

        return r;
    }

    private static float globalReverbMultiplier() {
        return 0.7F * SoundPhysicsMod.CONFIG.reverbGain.get();
    }

    private static float gainLf() {
        if (SoundPhysicsMod.CONFIG == null) {
            return 1F;
        }
        return SoundPhysicsMod.CONFIG.recordGainLf.get();
    }

    private static float decayLfRatio() {
        if (SoundPhysicsMod.CONFIG == null) {
            return 1.35F;
        }
        return SoundPhysicsMod.CONFIG.recordDecayLfRatio.get();
    }
}
