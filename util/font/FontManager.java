package luminac.util.font;

import java.awt.Font;
import java.io.InputStream;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class FontManager implements Manager {
    private TTFFontRenderer defaultFont;
    public FontManager getInstance() {
        return instance;
    }
    public TTFFontRenderer getFont(String key) {//, int size, boolean bold) {
        return fonts.getOrDefault(key, defaultFont);
    }
    private FontManager instance;
    private HashMap<String, TTFFontRenderer> fonts = new HashMap<>();

    @Override
    public void setup() {
    }

    public FontManager() {
        instance = this;
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(8);
        ConcurrentLinkedQueue<TextureData> textureQueue = new ConcurrentLinkedQueue<>();
        defaultFont = new TTFFontRenderer(executorService, textureQueue, new Font("Verdana", Font.PLAIN, 18));
        try {
            for (int i : new int[]{2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 21, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 42, 44, 46, 48, 50, 52, 128}) {
                InputStream istream = getClass().getResourceAsStream("/assets/minecraft/luminac/fonts/comfortaa.ttf");
                Font myFont = Font.createFont(Font.PLAIN, istream);
                myFont = myFont.deriveFont(Font.PLAIN, i);
                fonts.put("comfortaa " + i, new TTFFontRenderer(executorService, textureQueue, myFont));
                //fonts.put("comfortaa-bold" + i, new TTFFontRenderer(executorService, textureQueue, myFont));
            }
            fonts.put("Verdana 12", new TTFFontRenderer(executorService, textureQueue, new Font("Verdana", Font.PLAIN, 12)));

            fonts.put("Verdana Bold 16", new TTFFontRenderer(executorService, textureQueue, new Font("Verdana Bold", Font.PLAIN, 16)));
            fonts.put("Verdana Bold 20", new TTFFontRenderer(executorService, textureQueue, new Font("Verdana Bold", Font.PLAIN, 20)));
        } catch (Exception ignored) {

        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (!textureQueue.isEmpty()) {
                TextureData textureData = textureQueue.poll();
                GlStateManager.bindTexture(textureData.getTextureId());

                // Sets the texture parameter stuff.
                GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
                GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

                // Uploads the texture to opengl.
                GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, textureData.getWidth(), textureData.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, textureData.getBuffer());
            }
        }
    }
}