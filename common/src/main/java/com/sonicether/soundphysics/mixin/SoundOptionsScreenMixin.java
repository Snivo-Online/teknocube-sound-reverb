package com.sonicether.soundphysics.mixin;

import com.sonicether.soundphysics.SoundPhysics;
import com.sonicether.soundphysics.SoundPhysicsMod;
import com.sonicether.soundphysics.gui.PhysicsCategoryOptionsScreen;
import de.maxhenkel.configbuilder.entry.ConfigEntry;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.client.gui.screens.options.SoundOptionsScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SoundOptionsScreen.class)
public abstract class SoundOptionsScreenMixin extends OptionsSubScreen {

    public SoundOptionsScreenMixin(Screen lastScreen, Options options, Component title) {
        super(lastScreen, options, title);
    }

    @Inject(method = "addOptions", at = @At("RETURN"))
    private void soundPhysicsTeknocube$addReverbToggles(CallbackInfo ci) {
        if (SoundPhysicsMod.CONFIG == null || this.list == null) {
            return;
        }
        this.list.addHeader(Component.translatable("options.sound_physics_remastered.header"));
        this.list.addSmall(
                booleanToggle(
                        "options.sound_physics_remastered.reverb",
                        "options.sound_physics_remastered.reverb.tooltip",
                        SoundPhysicsMod.CONFIG.reverbEnabled
                ),
                booleanToggle(
                        "options.sound_physics_remastered.record_reverb",
                        "options.sound_physics_remastered.record_reverb.tooltip",
                        SoundPhysicsMod.CONFIG.recordReverb
                )
        );
        this.list.addBig(Button.builder(
                Component.translatable("options.sound_physics_remastered.physics_categories"),
                button -> this.minecraft.gui.setScreen(new PhysicsCategoryOptionsScreen(this, this.options))
        ).tooltip(Tooltip.create(Component.translatable("options.sound_physics_remastered.physics_categories.tooltip"))).build());
        this.list.addBig(percentSlider(
                "options.sound_physics_remastered.record_bass",
                "options.sound_physics_remastered.record_bass.tooltip",
                SoundPhysicsMod.CONFIG.recordReverbBass,
                0F,
                1F,
                false
        ));
        this.list.addBig(percentSlider(
                "options.sound_physics_remastered.record_body",
                "options.sound_physics_remastered.record_body.tooltip",
                SoundPhysicsMod.CONFIG.recordReverbBody,
                0.5F,
                2.5F,
                false
        ));
        this.list.addBig(percentSlider(
                "options.sound_physics_remastered.record_late",
                "options.sound_physics_remastered.record_late.tooltip",
                SoundPhysicsMod.CONFIG.recordLateSend,
                0.5F,
                4F,
                false
        ));
        this.list.addBig(percentSlider(
                "options.sound_physics_remastered.record_facing",
                "options.sound_physics_remastered.record_facing.tooltip",
                SoundPhysicsMod.CONFIG.recordFacingReverb,
                0F,
                2F,
                false
        ));
        this.list.addBig(percentSlider(
                "options.sound_physics_remastered.record_impact",
                "options.sound_physics_remastered.record_impact.tooltip",
                SoundPhysicsMod.CONFIG.recordFacingImpact,
                0F,
                2F,
                false
        ));
        this.list.addSmall(
                percentSlider(
                        "options.sound_physics_remastered.gain_lf",
                        "options.sound_physics_remastered.gain_lf.tooltip",
                        SoundPhysicsMod.CONFIG.recordGainLf,
                        0F,
                        1F,
                        true
                ),
                percentSlider(
                        "options.sound_physics_remastered.decay_lf",
                        "options.sound_physics_remastered.decay_lf.tooltip",
                        SoundPhysicsMod.CONFIG.recordDecayLfRatio,
                        0.1F,
                        2F,
                        true
                )
        );
    }

    @Unique
    private static OptionInstance<Boolean> booleanToggle(String caption, String tooltip, ConfigEntry<Boolean> entry) {
        return OptionInstance.createBoolean(
                caption,
                OptionInstance.cachedConstantTooltip(Component.translatable(tooltip)),
                entry.get(),
                value -> entry.set(value).save()
        );
    }

    @Unique
    private static OptionInstance<Integer> percentSlider(
            String caption,
            String tooltip,
            ConfigEntry<Float> entry,
            float min,
            float max,
            boolean syncEfx
    ) {
        int minPct = Math.round(min * 100F);
        int maxPct = Math.round(max * 100F);
        int current = Mth.clamp(Math.round(entry.get() * 100F), minPct, maxPct);
        return new OptionInstance<>(
                caption,
                OptionInstance.cachedConstantTooltip(Component.translatable(tooltip)),
                (text, value) -> Component.translatable("options.percent_value", text, value),
                new OptionInstance.IntRange(minPct, maxPct),
                current,
                value -> {
                    entry.set(value / 100F).save();
                    if (syncEfx) {
                        SoundPhysics.syncReverbParams();
                    }
                }
        );
    }
}
