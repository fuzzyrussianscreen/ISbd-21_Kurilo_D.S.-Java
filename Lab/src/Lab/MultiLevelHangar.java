package Lab;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MultiLevelHangar {

	ArrayList<Hangar<IAircraft>> hangarStages;
	private int pictureWidth;
	private int pictureHeight;
	private final int countPlaces = 16;
	int countStages;
	public MultiLevelHangar(int countStages, int pictureWidth, int pictureHeight)
	{
		this.countStages=countStages;
		this.pictureWidth=pictureWidth;
		this.pictureHeight=pictureHeight;
		hangarStages = new ArrayList<Hangar<IAircraft>>(countStages);
		for (int i = 0; i < countStages; ++i)
		{
			hangarStages.add(new Hangar<IAircraft>(countPlaces, pictureWidth, pictureHeight));
		}
	}
	public Hangar<IAircraft> get(int ind)
	{
		if (ind > -1 && ind < hangarStages.size())
		{
			return hangarStages.get(ind);
		}
		return null;
	}
	
	public boolean SaveData(String filename) {
		File file = new File(filename);
		if (file.exists())
			file.delete();
		try (FileOutputStream fileStream = new FileOutputStream(file)) {
			try (BufferedOutputStream bs = new BufferedOutputStream(fileStream)) {
				String str = "CountLeveles:" + hangarStages.size() + System.lineSeparator();
				ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
				for (int i = 0; i < str.length(); i++) {
					byteOut.write(str.charAt(i));
				}
				byte[] info = byteOut.toByteArray();
				fileStream.write(info, 0, info.length);
				for (Hangar<IAircraft> level : hangarStages) {
					byteOut = new ByteArrayOutputStream();
					str = "Level" + System.lineSeparator();
					for (int i = 0; i < str.length(); i++) {
						byteOut.write(str.charAt(i));
					}
					info = byteOut.toByteArray();
					fileStream.write(info, 0, info.length);
					for (int i = 0; i < countPlaces; i++) {
						IAircraft fighter = level.getFighter(i);
						if (fighter != null) {
							byteOut = new ByteArrayOutputStream();
							String fighterInfo = fighter.getClass().getName() + ":" + fighter.getInfo() + System.lineSeparator();
							fighterInfo = fighterInfo.substring(5);
							for (int j = 0; j < fighterInfo.length(); j++) {
								byteOut.write(fighterInfo.charAt(j));
							}
							info = byteOut.toByteArray();
							fileStream.write(info, 0, info.length);
						}
					}
				}
			}
			fileStream.close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	
	public boolean load(String filename) {
		File file = new File(filename);
		if (!file.exists()) {
			return false;
		}
		try (FileInputStream fileStream = new FileInputStream(filename)) {
			String s = "";
			try (BufferedInputStream bs = new BufferedInputStream(fileStream)) {
				Path path = Paths.get(file.getAbsolutePath());
				byte[] b = new byte[fileStream.available()];
				b = Files.readAllBytes(path);
				ByteArrayInputStream bos = new ByteArrayInputStream(b);
				String value = new String(b, StandardCharsets.UTF_8);
				while (bos.read(b, 0, b.length) > 0) {
					s += value;
				}
				s = s.replace("\r", "");
				String[] strs = s.split("\n");
				if (strs[0].contains("CountLeveles")) {
					if (hangarStages != null) {
						hangarStages.clear();
					}
					hangarStages = new ArrayList<Hangar<IAircraft>>();
				} else {
					return false;
				}
				int counter = -1;
				for (int i = 0; i < strs.length; i++) {
					if (strs[i].startsWith("Level")) {
						counter++;
						hangarStages.add(new Hangar<IAircraft>(countPlaces, pictureWidth, pictureHeight));
					} else if (strs[i].startsWith("Plane")) {
						IAircraft fighter = new Plane(strs[i].split(":")[1]);
						int number = hangarStages.get(counter).addFighter(fighter);
						if (number == -1) {
							return false;
						}
					} else if (strs[i].startsWith("Fighter")) {
						IAircraft fighter = new Fighter(strs[i].split(":")[1]);
						int number = hangarStages.get(counter).addFighter(fighter);
						if (number == -1) {
							return false;
						}
					}
				}
			}
			return true;
		} catch (IOException ex) {
			return false;
		}
	}
}
