package effect;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation {
    private String name;
    private boolean isRepeated;

    private ArrayList<FrameImage> frameImages;
    private int currentFrame;
    private ArrayList<Boolean> ignoreFrame;
    private ArrayList<Double> delayFrame;
    private long beginTime;
    private boolean drawRectFrame;
    public Animation(){
        delayFrame =new ArrayList<Double>();
        beginTime = 0;
        currentFrame = 0;
        ignoreFrame = new ArrayList<Boolean>();
        frameImages = new ArrayList<FrameImage>();
        drawRectFrame = false;
        isRepeated = true;

    }
    public Animation(Animation animation){
        beginTime = animation.beginTime;
        currentFrame = animation.currentFrame;
        drawRectFrame = animation.drawRectFrame;
        isRepeated = animation.isRepeated;

        delayFrame = new ArrayList<Double>();
        for(Double d : animation.delayFrame){
            delayFrame.add(d);
        }
        ignoreFrame = new ArrayList<Boolean>();
        for(boolean b : animation.ignoreFrame) {
            ignoreFrame.add(b);
        }
        frameImages = new ArrayList<FrameImage>();
        for(FrameImage f : animation.frameImages){
            frameImages.add(f);
        }

    }
    public String getName() {
        return name;
    }

    public boolean getRepeated() {
        return isRepeated;
    }

    public ArrayList<FrameImage> getFrameImages() {
        return frameImages;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public ArrayList<Boolean> getIgnoreFrame() {
        return ignoreFrame;
    }

    public ArrayList<Double> getDelayFrame() {
        return delayFrame;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public boolean getDrawRectFrame() {
        return drawRectFrame;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRepeated(boolean repeated) {
        isRepeated = repeated;
    }

    public void setFrameImages(ArrayList<FrameImage> frameImages) {
        this.frameImages = frameImages;
    }

    public void setCurrentFrame(int currentFrame) {
        if(currentFrame>=0 && currentFrame < frameImages.size()){
            this.currentFrame = currentFrame;
        }else{
            this.currentFrame =0;
        }
    }

    public void setIgnoreFrame(ArrayList<Boolean> ignoreFrame) {
        this.ignoreFrame = ignoreFrame;
    }

    public void setDelayFrame(ArrayList<Double> delayFrame) {
        this.delayFrame = delayFrame;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public void setDrawRectFrame(boolean drawRectFrame) {
        this.drawRectFrame = drawRectFrame;
    }
    public boolean isIngoreFrame(int id){
        return ignoreFrame.get(id);
    }
    public void setIgnoreFrame(int id){
        if(id >=0&& id < ignoreFrame.size()){
            ignoreFrame.set(id,true);
        }
    }
    public void unIgnoreFrame(int id){
        if(id >=0&& id < ignoreFrame.size()){
            ignoreFrame.set(id,false);
        }
    }
    public void reset(){
        currentFrame = 0;
        beginTime = 0;

        for(int i = 0; i < ignoreFrame.size(); i++){
            ignoreFrame.set(i, false);
        }
    }
    public void add(FrameImage frameImage, double timeToNextFrame){

        ignoreFrame.add(false);
        frameImages.add(frameImage);
        delayFrame.add(new Double(timeToNextFrame));
    }
    public BufferedImage getCurrentImage(){
        return frameImages.get(currentFrame).getImage();
    }
    public void Update(long currentTime){

        if(beginTime == 0) beginTime = currentTime;
        else{

            if(currentTime - beginTime > delayFrame.get(currentFrame)){
                nextFrame();
                beginTime = currentTime;
            }
        }

    }
    private void nextFrame(){
        if(currentFrame > frameImages.size() - 1){
            if(isRepeated){
                currentFrame = 0;
            }else {
                currentFrame++;
            }
            if(ignoreFrame.get(currentFrame)) nextFrame();
        }
    }
    public boolean isLastFrame(){
        if(currentFrame == frameImages.size() -1){
            return true;
        }else return false;
    }
    public void flipAllImage(){
        for(int i =0; i < frameImages.size(); i++){
            BufferedImage image = frameImages.get(i).getImage();

            AffineTransform tx = AffineTransform.getScaleInstance(-1,1);
            tx.translate(-image.getWidth(),0);

            AffineTransformOp op = new AffineTransformOp(tx,
                    AffineTransformOp.TYPE_BILINEAR);
            frameImages.get(i).setImage(image);


        }
    }
    public  void draw(int x, int y, Graphics2D g2){
        BufferedImage image = getCurrentImage();
        g2.drawImage(image, x - image.getWidth()/2, y - image.getHeight()/2, null);
        if(drawRectFrame){
            g2.drawRect(x - image.getWidth()/2, x - image.getWidth()/2, image.getWidth(), image.getHeight());
        }
    }

}
