package org.julia0.invMG;

import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {

    @Override
    public void onEnable(){
        new CMDs(this);
    }

    @Override
    public void onDisable(){

    }
}
