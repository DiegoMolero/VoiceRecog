package domain;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;


public class Microphone {
    private TargetDataLine microphone;
    private  byte[] data;
    private ByteArrayOutputStream out;
    private int CHUNK_SIZE = 1024;
    private int numBytesRead;
    private AudioFormat format;
    private  DataLine.Info info;
    private AudioInputStream audioInputStream;
    private SourceDataLine sourceDataLine;
    
	public Microphone() {
		

	    try {
	    	format = new AudioFormat(8000.0f, 16, 1, true, true);
	        microphone = AudioSystem.getTargetDataLine(format);
	        info = new DataLine.Info(TargetDataLine.class, format);
	        DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, format);
	        microphone = (TargetDataLine) AudioSystem.getLine(info);

	        out = new ByteArrayOutputStream();
	        data = new byte[microphone.getBufferSize() / 5];
	        
	    } catch (LineUnavailableException e) {
	        e.printStackTrace();
	    } 
	}
	
	public void startMic(int sec){
		CounterSeconds counter = new CounterSeconds();
		 try {
			microphone.open(format);
			microphone.start();
			  numBytesRead =0;
			  while (counter.getAgeInSeconds() < sec) {
		            numBytesRead = microphone.read(data, 0, CHUNK_SIZE);
		            out.write(data, 0, numBytesRead); 
		        }
			  microphone.close();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		  
	}
	
	public void playMic(){
        try {
        byte audioData[] = out.toByteArray();
        InputStream byteArrayInputStream = new ByteArrayInputStream(
                audioData);
        audioInputStream = new AudioInputStream(byteArrayInputStream,format, audioData.length / format.getFrameSize());
        DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, format);
        sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
        sourceDataLine.open(format);
        sourceDataLine.start();
        int cnt = 0;
        byte tempBuffer[] = new byte[10000];

            while ((cnt = audioInputStream.read(tempBuffer, 0,tempBuffer.length)) != -1) {
                if (cnt > 0) {

                    sourceDataLine.write(tempBuffer, 0, cnt);
                }
            }
        } catch (IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        sourceDataLine.drain();
        sourceDataLine.close();
        microphone.close();
	}
}
