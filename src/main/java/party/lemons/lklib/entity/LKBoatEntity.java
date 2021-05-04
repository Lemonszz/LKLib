package party.lemons.lklib.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.CustomPayloadS2CPacket;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import party.lemons.lklib.init.LKEntities;
import party.lemons.lklib.init.LKNetwork;
import party.lemons.lklib.util.entity.EntityUtil;
import party.lemons.lklib.util.registry.boat.BoatType;
import party.lemons.lklib.util.registry.boat.BoatTypes;

public class LKBoatEntity extends BoatEntity
{
    private static final TrackedData<String> BOAT_TYPE = DataTracker.registerData(LKBoatEntity.class, TrackedDataHandlerRegistry.STRING);
    private static final String TAG_TYPE = "NewType";

    public LKBoatEntity(EntityType<? extends BoatEntity> entityType, World world)
    {
        super(entityType, world);
    }

    public LKBoatEntity(World world, double x, double y, double z)
    {
        this(LKEntities.LK_BOAT, world);
        this.updatePosition(x, y, z);
        this.setVelocity(Vec3d.ZERO);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
    }

    public LKBoatEntity(BoatEntity boatEntity, BoatType type)
    {
        this(LKEntities.LK_BOAT, boatEntity.world);

        this.copyPositionAndRotation(boatEntity);
        setBoatType(type);
    }

    @Override
    protected void initDataTracker()
    {
        super.initDataTracker();
        this.dataTracker.startTracking(BOAT_TYPE, "minecraft:oak");
    }

    public void setBoatType(BoatType type)
    {
        this.getDataTracker().set(BOAT_TYPE, type.id.toString());
    }

    public BoatType getNewBoatType()
    {
        return BoatTypes.REGISTRY.get(new Identifier(this.getDataTracker().get(BOAT_TYPE)));
    }

    @Override
    public Item asItem()
    {
        return getNewBoatType().item.get().asItem();
    }

    @Override
    protected void writeCustomDataToTag(CompoundTag tag)
    {
        tag.putString(TAG_TYPE, this.getNewBoatType().id.toString());
    }

    @Override
    protected void readCustomDataFromTag(CompoundTag tag)
    {
        if(tag.contains(TAG_TYPE, 8))
        {
            this.setBoatType(BoatTypes.REGISTRY.get(new Identifier(tag.getString(TAG_TYPE))));
        }
    }
    @Override
    public Packet<?> createSpawnPacket()
    {
        return new CustomPayloadS2CPacket(LKNetwork.SPAWN_ENTITY, EntityUtil.WriteEntitySpawn(this));
    }
}