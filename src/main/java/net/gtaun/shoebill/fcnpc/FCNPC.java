package net.gtaun.shoebill.fcnpc;

import net.gtaun.shoebill.amx.types.ReferenceFloat;
import net.gtaun.shoebill.amx.types.ReferenceInt;
import net.gtaun.shoebill.constant.SkinModel;
import net.gtaun.shoebill.constant.SpecialAction;
import net.gtaun.shoebill.constant.WeaponModel;
import net.gtaun.shoebill.data.Quaternion;
import net.gtaun.shoebill.data.Vector3D;
import net.gtaun.shoebill.data.Velocity;
import net.gtaun.shoebill.exception.CreationFailedException;
import net.gtaun.shoebill.fcnpc.data.Keys;
import net.gtaun.shoebill.fcnpc.constant.MoveType;
import net.gtaun.shoebill.object.Destroyable;
import net.gtaun.shoebill.object.Vehicle;

/**
 * Created by marvin on 16.01.16.
 * Copyright (c) 2015 Marvin Haschker. All rights reserved.
 */
public class FCNPC implements Destroyable {

    private int id;
    private String name;

    public static FCNPC create(String name) {
        int id = Functions.FCNPC_Create(name);
        if(id == Wrapper.INVALID_ENTITY_ID) throw new CreationFailedException("FCNPC_Create() returned an invalid entity id.");
        FCNPC npc = new FCNPC(id, name);
        Wrapper.getInstance().addNpc(npc);
        return npc;
    }

    private FCNPC(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public SkinModel getSkin() {
        return SkinModel.getById(Functions.FCNPC_GetSkin(id));
    }

    public void setSkin(SkinModel model) {
        Functions.FCNPC_SetSkin(id, model.getId());
    }

    public void spawn(SkinModel model, Vector3D position) {
        Functions.FCNPC_Spawn(id, model.getId(), position.x, position.y, position.z);
    }

    public void respawn() {
        Functions.FCNPC_Respawn(id);
    }

    public boolean isSpawned() {
        return Functions.FCNPC_IsSpawned(id) > 0;
    }

    public void kill() {
        Functions.FCNPC_Kill(id);
    }

    public boolean isDead() {
        return Functions.FCNPC_IsDead(id) > 0;
    }

    public void setPosition(Vector3D position) {
        Functions.FCNPC_SetPosition(id, position.x, position.y, position.z);
    }

    public Vector3D getPosition() {
        ReferenceFloat x = new ReferenceFloat(0f), y = new ReferenceFloat(0f), z = new ReferenceFloat(0f);
        Functions.FCNPC_GetPosition(id, x, y, z);
        return new Vector3D(x.getValue(), y.getValue(), z.getValue());
    }

    public void setAngle(float angle) {
        Functions.FCNPC_SetAngle(id, angle);
    }

    public float getAngle() {
        return Functions.FCNPC_GetAngle(id);
    }

    public void setQuaternion(Quaternion quaternion) {
        Functions.FCNPC_SetQuaternion(id, quaternion.x, quaternion.y, quaternion.z, quaternion.w);
    }

    public Quaternion getQuaternion() {
        ReferenceFloat x = new ReferenceFloat(0f), y = new ReferenceFloat(0f), z = new ReferenceFloat(0f), w = new ReferenceFloat(0f);
        Functions.FCNPC_GetQuaternion(id, x, y, z, w);
        return new Quaternion(x.getValue(), y.getValue(), z.getValue(), w.getValue());
    }

    public void setVelocity(Velocity velocity) {
        Functions.FCNPC_SetVelocity(id, velocity.x, velocity.y, velocity.z);
    }

    public Velocity getVelocity() {
        ReferenceFloat x = new ReferenceFloat(0f), y = new ReferenceFloat(0f), z = new ReferenceFloat(0f);
        Functions.FCNPC_GetVelocity(id, x, y, z);
        return new Velocity(x.getValue(), y.getValue(), z.getValue());
    }

    public void setInterior(int interior) {
        Functions.FCNPC_SetInterior(id, interior);
    }

    public int getInterior() {
        return Functions.FCNPC_GetInterior(id);
    }

    public void setHealth(float health) {
        Functions.FCNPC_SetHealth(id, health);
    }

    public float getHealth() {
        return Functions.FCNPC_GetHealth(id);
    }

    public void setArmour(int armour) {
        Functions.FCNPC_SetArmour(id, armour);
    }

    public float getArmour() {
        return Functions.FCNPC_GetAmmo(id);
    }

    public void setWeapon(WeaponModel weaponModel) {
        Functions.FCNPC_SetWeapon(id, weaponModel.getId());
    }

    public WeaponModel getWeapon() {
        return WeaponModel.get(Functions.FCNPC_GetWeapon(id));
    }

    public void setAmmo(int amount) {
        Functions.FCNPC_SetAmmo(id, amount);
    }

    public int getAmmo() {
        return Functions.FCNPC_GetAmmo(id);
    }

    public void setKeys(int keys) {
        Functions.FCNPC_SetKeys(id, keys);
    }

    public Keys getKeys() {
        ReferenceInt udAnalog = new ReferenceInt(0), lrAnalog = new ReferenceInt(0), keys = new ReferenceInt(0);
        Functions.FCNPC_GetKeys(id, udAnalog, lrAnalog, keys);
        return new Keys(udAnalog.getValue(), lrAnalog.getValue(), keys.getValue());
    }

    public void setSpecialAction(SpecialAction specialAction) {
        Functions.FCNPC_SetSpecialAction(id, specialAction.getValue());
    }

    public SpecialAction getSpecialAction() {
        return SpecialAction.get(Functions.FCNPC_GetSpecialAction(id));
    }

    public void toggleReloading(boolean toggle) {
        Functions.FCNPC_ToggleReloading(id, toggle);
    }

    public void toggleInfiniteAmmo(boolean toggle) {
        Functions.FCNPC_ToggleInfiniteAmmo(id, toggle);
    }

    public void goTo(Vector3D location, MoveType moveType, float speed, boolean useZMap) {
        Functions.FCNPC_GoTo(id, location.x, location.y, location.z, moveType.getValue(), speed, useZMap ? 1 : 0);
    }

    public void stop() {
        Functions.FCNPC_Stop(id);
    }

    public boolean isMoving() {
        return Functions.FCNPC_IsMoving(id) > 0;
    }

    public void aimAt(Vector3D location, boolean shoot) {
        Functions.FCNPC_AimAt(id, location.x, location.y, location.z, shoot ? 1 : 0);
    }

    public void stopAim() {
        Functions.FCNPC_StopAim(id);
    }

    public void meleeAttack(int delay) {
        Functions.FCNPC_MeleeAttack(id, delay);
    }

    public void stopAttack() {
        Functions.FCNPC_StopAttack(id);
    }

    public boolean isAiming() {
        return Functions.FCNPC_IsAiming(id) > 0;
    }

    public boolean isShooting() {
        return Functions.FCNPC_IsShooting(id) > 0;
    }

    public boolean isReloading() {
        return Functions.FCNPC_IsReloading(id) > 0;
    }

    public void enterVehicle(Vehicle vehicle, int seatid, MoveType moveType) {
        Functions.FCNPC_EnterVehicle(id, vehicle.getId(), seatid, moveType.getValue());
    }

    public void exitVehicle() {
        Functions.FCNPC_ExitVehicle(id);
    }

    public void putInVehicle(Vehicle vehicle, int seat) {
        Functions.FCNPC_PutInVehicle(id, vehicle.getId(), seat);
    }

    public void removeFromVehicle() {
        Functions.FCNPC_RemoveFromVehicle(id);
    }

    public Vehicle getVehicle() {
        return Vehicle.get(Functions.FCNPC_GetVehicleID(id));
    }

    public int getVehicleSeat() {
        return Functions.FCNPC_GetVehicleSeat(id);
    }

    public void startPlayingPlayback(String file) {
        Functions.FCNPC_StartPlayingPlayback(id, file);
    }

    public void stopPlayingPlayback() {
        Functions.FCNPC_StopPlayingPlayback(id);
    }

    public void pausePlayingPlayback() {
        Functions.FCNPC_PausePlayingPlayback(id);
    }

    public void resumePlayingPlayback() {
        Functions.FCNPC_ResumePlayingPlayback(id);
    }

    public void playNode(int node, MoveType moveType) {
        Functions.FCNPC_PlayNode(id, node, moveType.getValue());
    }

    public void stopPlayingNode() {
        Functions.FCNPC_StopPlayingNode(id);
    }

    @Override
    public void destroy() {
        if(isDestroyed()) return;

        Functions.FCNPC_Destroy(id);
        id = Wrapper.INVALID_ENTITY_ID;
    }

    @Override
    public boolean isDestroyed() {
        return id == Wrapper.INVALID_ENTITY_ID;
    }
}