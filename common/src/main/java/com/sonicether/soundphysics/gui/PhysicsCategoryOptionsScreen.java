package com.sonicether.soundphysics.gui;

import com.sonicether.soundphysics.SoundPhysicsMod;
import com.sonicether.soundphysics.config.SoundPhysicsConfig;
import de.maxhenkel.configbuilder.entry.ConfigEntry;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;

public class PhysicsCategoryOptionsScreen extends OptionsSubScreen {

    public PhysicsCategoryOptionsScreen(Screen lastScreen, Options options) {
        super(lastScreen, options, Component.translatable("options.sound_physics_remastered.physics_categories"));
    }

    @Override
    protected void addOptions() {
        if (SoundPhysicsMod.CONFIG == null || this.list == null) {
            return;
        }
        SoundSource[] categories = SoundPhysicsConfig.physicsCategoryOrder();
        this.list.addBig(categoryToggle(categories[0]));
        for (int i = 1; i < categories.length; i += 2) {
            if (i + 1 < categories.length) {
                this.list.addSmall(categoryToggle(categories[i]), categoryToggle(categories[i + 1]));
            } else {
                this.list.addBig(categoryToggle(categories[i]));
            }
        }
    }

    private static OptionInstance<Boolean> categoryToggle(SoundSource category) {
        ConfigEntry<Boolean> entry = SoundPhysicsMod.CONFIG.physicsFor(category);
        return OptionInstance.createBoolean(
                "soundCategory." + category.getName(),
                OptionInstance.cachedConstantTooltip(Component.translatable("options.sound_physics_remastered.physics_category.tooltip")),
                entry.get(),
                value -> entry.set(value).save()
        );
    }
}
