package org.theoliverlear.oliversadditions.entities;
//=================================-Imports-==================================
import lombok.Getter;
import lombok.Setter;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Getter
@Setter
public class PluckableChickenEntity extends ChickenEntity {
    //============================-Constants-=================================
    private static final Logger LOGGER = LogManager.getLogger();
    public static final int MAX_PLUCK_COUNT = 4;
    public static final int TICKS_TO_GROW_FEATHER = 1200;
    public static final DataParameter<Boolean> IS_PLUCKED = EntityDataManager.defineId(PluckableChickenEntity.class, DataSerializers.BOOLEAN);
    //============================-Variables-=================================
    private boolean isPlucked;
    private int pluckCount;
    private int ticksSinceLastPluck;
    //===========================-Constructors-===============================
    public PluckableChickenEntity(EntityType<? extends ChickenEntity> chickenType,
                                  World fromWorld) {
        super(chickenType, fromWorld);
        this.isPlucked = false;
        this.pluckCount = 0;
        this.ticksSinceLastPluck = 0;
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(4.0D);
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.25D);
        LOGGER.info("PluckableChickenEntity constructor called");
    }
    //=============================-Methods-==================================

    //-------------------------------Pluck------------------------------------
    public void pluck() {
        if (this.pluckCount < MAX_PLUCK_COUNT) {
            LOGGER.info("Chicken plucked");
            this.pluckCount++;
            this.ticksSinceLastPluck = 0;
        }
        if (this.pluckCount == MAX_PLUCK_COUNT) {
            LOGGER.info("Chicken fully plucked");
            this.isPlucked = true;
            this.entityData.set(IS_PLUCKED, true);
            this.setDeltaMovement(this.getDeltaMovement().add(0, 0.05, 0));
        }
    }
    //============================-Overrides-=================================
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_PLUCKED, false);
    }
    //--------------------------------Tick------------------------------------
    @Override
    public void tick() {
        super.tick();
        this.ticksSinceLastPluck++;
        if (this.ticksSinceLastPluck >= TICKS_TO_GROW_FEATHER && this.pluckCount > 0) {
            this.pluckCount--;
            this.ticksSinceLastPluck = 0;
            if (this.isPlucked) {
                this.isPlucked = false;
                this.entityData.set(IS_PLUCKED, false);
            }
        }
    }
    //-------------------------Create-Attributes------------------------------
    // Overridden but not annotated with @Override.
    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return ChickenEntity.createAttributes();
    }
    //----------------------Add-Additional-Save-Data--------------------------
    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("isPlucked", this.isPlucked);
        compound.putInt("pluckCount", this.pluckCount);
    }
    //---------------------Read-Additional-Save-Data--------------------------
    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        this.isPlucked = compound.getBoolean("isPlucked");
        this.pluckCount = compound.getInt("pluckCount");
    }
}
