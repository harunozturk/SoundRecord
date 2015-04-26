

import javax.sound.sampled.*;

import java.io.*;

public class JavaSoundRecorder {
	String path;
	File wavFile;

 
    public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	    wavFile = new File(path);

	}    
 
    AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
 
    TargetDataLine line;
 
    AudioFormat getAudioFormat() {
        float sampleRate = 22050;
        int sampleSizeInBits = 16;
        int channels = 1;
        boolean signed = true;
        boolean bigEndian = true;
        AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits,
                                             channels, signed, bigEndian);
        return format;
    }
 
    void start() {
        try {
            AudioFormat format = getAudioFormat();
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
 
            if (!AudioSystem.isLineSupported(info)) {
                System.exit(0);
            }
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();
 
 
            AudioInputStream ais = new AudioInputStream(line);
 
 
            AudioSystem.write(ais, fileType, wavFile);
 
        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
 
    void finish() {
        line.stop();
        line.close();
    }

}