//package luminac.modules.combat;
//
//import luminac.events.Event;
//import luminac.events.listeners.EventMotion;
//import luminac.modules.Module;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.util.MathHelper;
//import org.lwjgl.input.Keyboard;
//
//import java.util.Comparator;
//
//public class TargetStrafe extends Module {
////    Setting speed = new Setting("Speed", this, 2.86, 0, 10, false);
////    Setting range = new Setting("Range", this, 5, 0, 6, false);
////    Setting rubber0 = new Setting("RubberbandCheck", this, true);
////    Setting rubberAmount = new Setting("RubberAmount", this, 0.3, 0, 1, false);
////    Setting rubberTime = new Setting("RubberTime", this, 0.3, 0, 1, false);
////    Setting rubberMove = new Setting("RubberMove", this, 0.3, 0, 1, false);
////    Setting erange = new Setting("Player Range", this, 10, 0, 20, false);
////    Setting wallcheck = new Setting("Wallcheck", this, true);
//
//    public TargetStrafe() {
//        super("TargetStrafe", Keyboard.KEY_NONE, Category.COMBAT);
////        this.addSettings(range, erange, speed, wallcheck, rubber0, rubberAmount, rubberTime, rubberMove);
//    }
//
//    mode curMode = mode.UP;
//    int height = 0;
//    SalTimer timer = new SalTimer();
//    EntityPlayer target;
//    double m_angle = 999;
//    boolean hitwall = false;
//    boolean bypass = false;
//    boolean rubber = false;
//    boolean moved = false;
//    private Rainbow rainbow = new Rainbow();
//
//    public void onEnable() {
//        this.m_angle = 999;
//        this.hitwall = false;
//        this.target = null;
//    }
//
//    public void onDisable() {
//        this.m_angle = 999;
//        mc.thePlayer.motionX = 0;
//        mc.thePlayer.motionZ = 0;
//    }
//
//    public void onEvent(Event e) {
//        if(e instanceof EventMotion) {
//
//            if(mc.theWorld == null || mc.thePlayer == null) return;
//
//            if(target == null) {
//                this.target = mc.theWorld.loadedEntityList.stream()
//                        .filter(this::isValidEntity)
//                        .map(entity1 -> (EntityPlayer) entity1)
//                        .min(Comparator.comparing(c -> mc.thePlayer.getDistance(c.posX, c.posY, c.posZ)))
//                        .orElse(null);
//            }
//
//            if(target == null || target.isDead || (int) target.getHealth() <= 0) {
//                this.target = null;
//                this.hitwall = false;
//                this.m_angle = 999;
//                return;
//            }
//
//            if(this.rubber) {
//                if(!moved) {
//                    mc.thePlayer.motionX = rubberMove.getValDouble() / 2.5;
//                    mc.thePlayer.motionZ = rubberMove.getValDouble() / 2.5;
//                    this.moved = true;
//                }
//                if(!timer.passed(rubberTime.getValDouble() * 1000)) {
//                    return;
//                } else {
//                    this.rubber = false;
//                    this.moved = true;
//                }
//            }
//
//            double xDif = target.posX - mc.thePlayer.posX;
//            double zDif = target.posZ - mc.thePlayer.posZ;
//
//            if(this.m_angle == 999) {
//                this.m_angle = MathHelper.atan2(xDif, zDif);
//            }
//
//            double x = (target.posX + (range.getValDouble() * Math.cos(Math.toRadians(m_angle))));
//            double z = (target.posZ + (range.getValDouble() * Math.sin(Math.toRadians(m_angle))));
//            double xMotion = (x - mc.thePlayer.posX);
//            double zMotion = (z - mc.thePlayer.posZ);
//
//            if(determineCollision(xMotion, 0, zMotion) && wallcheck.getValBoolean()) {
//                this.hitwall = !hitwall;
//                this.bypass = true;
//            } else if(!wallcheck.getValBoolean()) {
//                this.hitwall = false;
//            }
//
//            if(Math.abs(x - mc.thePlayer.posX) <= (speed.getValDouble() / 10) && Math.abs(z - mc.thePlayer.posZ) <= (speed.getValDouble() / 10) || bypass) {
//                this.m_angle = addAngle((this.hitwall ? (-speed.getValDouble() * 2) : (speed.getValDouble() * 2)) * (3 - (range.getValDouble() / 3)), m_angle);
//            }
//
//            event.x = NumberUtils.clamp(-speed.getValDouble() / 10, speed.getValDouble() / 10, xMotion);
//            event.z = NumberUtils.clamp(-speed.getValDouble() / 10, speed.getValDouble() / 10, zMotion);
//            this.bypass = false;
//
//            if(mc.thePlayer.onGround) {
//                event.y = mc.thePlayer.motionY = 0.405;
//            }
//
//            event.cancel();
//        });
//
//
//    public ArrayList<Block> isColliding(double posX, double posY, double posZ) {
//        ArrayList<Block> block = new ArrayList<>();
//        if (mc.thePlayer != null) {
//            final AxisAlignedBB bb = mc.thePlayer.ridingEntity != null ? mc.thePlayer.ridingEntity.getEntityBoundingBox().contract(0.0d, 0.0d, 0.0d).offset(posX, posY, posZ) : mc.thePlayer.getEntityBoundingBox().contract(0.0d, 1d, 0.0d).offset(posX, posY, posZ);
//            int y = (int) bb.minY;
//            for (int x = (int) Math.floor(bb.minX); x < Math.floor(bb.maxX) + 1; x++) {
//                for (int z = (int) Math.floor(bb.minZ); z < Math.floor(bb.maxZ) + 1; z++) {
//                    block.add(mc.theWorld.getBlockState(new BlockPos(x, y, z)).getBlock());
//                }
//            }
//        }
//        return block;
//    }
//
//    private boolean determineCollision(double x, double y, double z) {
//        for(Block block : isColliding(x, y, z)) {
//            if(block != Blocks.air) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    @SubscribeEvent
//    public void onRender(RenderWorldLastEvent event) {
//        if(target != null) {
//            rainbow.updateColor(3);
//            try {
//                Method setupCameraTransform = mc.entityRenderer.getClass().getDeclaredMethod(ProtonMod.developerEnvironment ? "setupCameraTransform" : "func_78479_a", float.class, int.class);
//                setupCameraTransform.setAccessible(true);
//
//                boolean bobbing = mc.gameSettings.viewBobbing;
//                mc.gameSettings.viewBobbing = false;
//                setupCameraTransform.invoke(mc.entityRenderer, event.partialTicks, 0);
//
//                if(this.height > 40 && curMode == mode.UP) {
//                    this.curMode = mode.DOWN;
//                } else if(this.height < 0 && curMode == mode.DOWN) {
//                    this.curMode = mode.UP;
//                }
//
//                if(curMode == mode.UP) {
//                    this.height++;
//                } else if(curMode == mode.DOWN) {
//                    this.height--;
//                }
//
//                float height0 = (height / 40f) * 2;
//                ProtonTessellator.drawCircle((float) target.posX, (float) target.posY + height0, (float) target.posZ, 0.5f, 3f, rainbow.getColor());
//                ProtonTessellator.drawCircle((float) target.posX, (float) target.posY + 0.3f, (float) target.posZ, (float) range.getValDouble(), 2f, rainbow.getColor());
//
//                mc.gameSettings.viewBobbing = bobbing;
//                setupCameraTransform.invoke(mc.entityRenderer, event.partialTicks, 0);
//            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @EventHandler
//    private final Listener<EventPacketReceive> test = new Listener<>(event -> {
//        if(!rubber0.getValBoolean()) return;
//        if(event.getPacket() instanceof S08PacketPlayerPosLook) {
//            S08PacketPlayerPosLook packet = (S08PacketPlayerPosLook) event.getPacket();
//
//            if(Math.abs(packet.getX() - mc.thePlayer.posX) > rubberAmount.getValDouble() || Math.abs(packet.getZ() - mc.thePlayer.posZ) > rubberAmount.getValDouble()) {
//                messageUtils.sendClientSideMessage(true, "Rubberband detected! pausing!");
//
//                this.rubber = true;
//                this.moved = false;
//                timer.reset();
//            }
//        }
//    });
//
//    private boolean isValidEntity(Entity entity) {
//        if(mc.thePlayer.getDistanceToEntity(entity) > erange.getValDouble()) return false;
//        if(entity instanceof EntityPlayer && entity != mc.thePlayer) {
//            EntityPlayer a = (EntityPlayer) entity;
//            return !mc.thePlayer.isOnSameTeam(a);
//        }
//        return false;
//    }
//
//    private double addAngle(double angle, double og) {
//        if(angle + og > 360) {
//            return angle + og - 360;
//        }
//        return og + angle;
//    }
//
//    private enum mode {
//        UP, DOWN
//    }
