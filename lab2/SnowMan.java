/** 
 * SnowMan.java:
 * 
 * Displays a simple snow man using multiple Wheels Shapes.
 * The entire snowman is built in the constructor.
 * 
 * @author rdb
 * Created 9/11/07; derived from cs415 demo program, Start.java 
 */
import java.awt.*;
import javax.swing.*;

public class SnowMan extends JComponent
{
   //---------------- instance variables ------------------------------
   // Components need to be accessed when displaying
   //
   private GEllipse    head;
   private GEllipse    body;
   private GEllipse    leftEye;
   private GEllipse    rightEye;
   private GRectangle  hatBody;
   private GRectangle  hatBrim;
   private GRectangle  mouth;
   private GLine       leftArm;
   private GLine       rightArm;
   
   private GLine[]      smile;
    
   private JFrame      frame;
   
   // -----------------------------------------------------------------
   /** 
    * Constructor for the SnowMan class. Arguments are the position.
    */
   public SnowMan( JFrame f, int x, int y )
   {
      // local "constant" variables are used to define the locations of each of the
      // components
      int    headX     = 25,  headY     = 0;
      int    headSize  = 50;
      int    bodyX     = 10,  bodyY     = 40;
      int    bodySize  = 80; 
      int    mouthX    = 40,  mouthY    = 30;
      int    mouthW    = 20,  mouthH    = 4;
      
      int[]  smileX     = { 40, 45, 55, 60 };
      int[]  smileY     = { 28, 30, 30, 28 };
      
      int    leftEyeX  = 40,  leftEyeY  = 15;
      int    rightEyeX = 55,  rightEyeY = 15;
      int    eyeSize   = 4;
      int    brimX  = headX + 5,  brimY  = headY;
      int    brimW = headSize - 10, brimH = 4;
      int    hatX   = 38,           hatY   = headY - 13;
      int    hatW   = headSize/2,   hatH   = 13;
      
      int    lArmX1 = 0, lArmY1 = 20, lArmX2 = 15, lArmY2 = 60;
      int    rArmX1 = 85, rArmY1 = 60, rArmX2 = 120, rArmY2 = 40;
      
      frame = f;
  
      // create the two eyes
      leftEye = new GEllipse( frame, x + leftEyeX, y + leftEyeY );
      leftEye.setColor( Color.BLACK );
      leftEye.setSize( eyeSize, eyeSize );
      
      rightEye = new GEllipse( frame, x + rightEyeX, y + rightEyeY );
      rightEye.setColor( Color.BLACK );
      rightEye.setSize( eyeSize, eyeSize );
       
      // create a smile
      smile = new GLine[ smileX.length - 1 ];
      for ( int i = 0; i < smileX.length - 1; i++ )
      {
        smile[ i ] = new GLine( frame, Color.BLACK );
        smile[ i ].setPoints(  x + smileX[ i ], y + smileY[ i ], 
                          x + smileX[ i + 1 ], y + smileY[ i + 1 ] );
        smile[ i ].setLineWidth( 2 );
      }
      
      // create the hat and its brim
      hatBody = new GRectangle( frame, x + hatX, y + hatY );
      hatBody.setSize( hatW, hatH );
      hatBody.setColor( Color.BLACK );
      
      hatBrim = new GRectangle( frame, x + brimX, y + brimY );
      hatBrim.setSize( brimW, brimH );
      hatBrim.setColor( Color.BLACK );
      
      // create the head
      head = new GEllipse( frame, x + headX, y + headY );
      head.setSize( headSize, headSize );
      head.setFillColor( new Color(255,255,255,128) );

      // create the body
      body = new GEllipse( frame, x + bodyX, y + bodyY );
      body.setSize( bodySize, bodySize );
      body.setFillColor( Color.WHITE );
      
      // create some arms
      leftArm = new GLine( frame, Color.BLACK );
      leftArm.setPoints( x + lArmX1, y + lArmY1, 
                         x + lArmX2, y + lArmY2 );
      leftArm.setLineWidth( 3 );
      rightArm = new GLine( frame, Color.BLACK  );
      rightArm.setPoints( x + rArmX1, y + rArmY1, 
                         x + rArmX2, y + rArmY2 );
      rightArm.setLineWidth( 3 );
   }
 
   /**/
}//End of Class SnowMan
