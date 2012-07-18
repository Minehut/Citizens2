package net.citizensnpcs.npc.entity;

import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.npc.CitizensMobNPC;
import net.citizensnpcs.npc.CitizensNPC;
import net.citizensnpcs.npc.ai.NPCHolder;
import net.minecraft.server.EntityChicken;
import net.minecraft.server.PathfinderGoalSelector;
import net.minecraft.server.World;

import org.bukkit.entity.Chicken;

public class CitizensChickenNPC extends CitizensMobNPC {

    public CitizensChickenNPC(int id, String name) {
        super(id, name, EntityChickenNPC.class);
    }

    @Override
    public Chicken getBukkitEntity() {
        return (Chicken) getHandle().getBukkitEntity();
    }

    public static class EntityChickenNPC extends EntityChicken implements NPCHolder {
        private final CitizensNPC npc;

        public EntityChickenNPC(World world) {
            this(world, null);
        }

        public EntityChickenNPC(World world, NPC npc) {
            super(world);
            this.npc = (CitizensNPC) npc;
            if (npc != null) {
                goalSelector = new PathfinderGoalSelector();
                targetSelector = new PathfinderGoalSelector();
            }
        }

        @Override
        public NPC getNPC() {
            return npc;
        }

        @Override
        public void z_() {
            super.z_();
            if (npc != null)
                npc.update();
        }

        @Override
        public void b_(double x, double y, double z) {
            // when another entity collides, b_ is called to push the NPC
            // so we prevent b_ from doing anything.
        }
    }
}