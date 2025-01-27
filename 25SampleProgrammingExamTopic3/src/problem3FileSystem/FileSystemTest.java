package problem3FileSystem;

import java.util.*;

public class FileSystemTest {

	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		Device device = new Device();
		Directory current = new Directory();
		StringBuilder output = new StringBuilder();

		String command = input.nextLine();
		if (command.startsWith("new")) {
			String commandAtributes = command.substring("new".length() + 1);
			String[] tokens = commandAtributes.split(",");
			device = new Device(tokens[0], new Directory(tokens[1], Calendar
					.getInstance().getTime()));
			current = device.getRoot();
			output.append("Device created – " + device.getName() + ", "
					+ device.getRoot() + "\n");
		}

		command = input.nextLine();
		while (!command.equals("end")) {
			if (command.startsWith("md")) {
				Directory newDirectory = makeDirectory(command);
				try {
					current.addDirectory(newDirectory);
				} catch (Exception e) {
					e.printStackTrace();
				}
				output.append("Directory made - " + newDirectory.getName()
						+ "\n");
			} else if (command.startsWith("cd")) {
				if (command.equals("cd ..")) {
					current = current.getParent();
				} else {
					String dirName = command.substring("cd".length() + 1)
							.trim();
					for (Directory dir : current.getDirectories()) {
						if (dir.getName().equals(dirName)) {
							current = dir;
						}
					}
				}
				output.append("Directory changed - " + current.getName() + "\n");
			} else if (command.startsWith("dir")) {
				output.append(current.getFilesAndDirs());
			} else if (command.equals("cur")) {
				output.append(current.getName() + "\n");
			} else if (command.startsWith("nf")) {
				try {
					output.append("File created – " + newFile(command, current)
							+ "\n");
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (command.startsWith("rf")) {
				output.append(readFile(command, current));
			} else if (command.startsWith("all")) {
				output.append(device.toString());
			}
			command = input.nextLine();
		}
		System.out.println(output);
		input.close();
	}

	static Directory makeDirectory(String command) {
		String commandAtributes = command.substring("md".length() + 1);
		String[] tokens = commandAtributes.split(",");
		Directory result = new Directory(tokens[0], getCurrentTime());
		return result;
	}

	static String newFile(String command, Directory current) throws Exception {
		String cmdAtributes = command.substring("nf".length() + 1);
		String[] tokens = cmdAtributes.split("\\.");

		String content = input.nextLine();

		if (tokens[1].equals("txt")) {
			TextFile newFile = new TextFile(tokens[0], Calendar.getInstance()
					.getTime(), getCurrentTime(), content);
			current.addFile(newFile);
			return newFile.toString();
		} else {
			String[] bytes = content.split(" ");
			byte[] bytesContent = new byte[bytes.length];
			for (int i = 0; i < bytes.length; i++) {
				bytesContent[i] = fromIntToByte(bytes[i]);
			}

			BinaryFile newFile = new BinaryFile(tokens[0], Calendar
					.getInstance().getTime(), getCurrentTime(), bytesContent);
			current.addFile(newFile);
			return newFile.toString();
		}

	}

	public static byte fromIntToByte(String value) throws Exception {
		return fromIntToByte(Integer.parseInt(value));
	}

	public static byte fromIntToByte(int value) throws Exception {
		String stringByte = "";
		if (value < 0 && value > 255) {
			throw new Exception("Must be from 0 <= value <= 255");
		}
		if (value <= 127) {
			for (int i = 0; i < 8; i++) {
				stringByte = String.valueOf(value % 2) + stringByte;
				value = value / 2;
			}
		} else {
			value = value / 2;
			for (int i = 0; i < 7; i++) {
				stringByte = String.valueOf(value % 2) + stringByte;
				value = value / 2;
			}
			stringByte = "-" + stringByte;
		}
//		System.out.println(stringByte);
		byte b = Byte.parseByte(stringByte, 2);
		return b;
	}

	static Date getCurrentTime() {
		return Calendar.getInstance().getTime();
	}

	static String readFile(String command, Directory currnet) {
		String fileName = command.substring("rf".length() + 1).trim();

		File file = null;
		for (File f : currnet.getFiles()) {
			if (f.getName().equals(fileName) || f.toString().equals(fileName)) {
				file = f;
				break;
			}
		}

		if (file.getType().equals(FilesType.Text)) {
			return file.getContent().toString() + "\n";
		}

		StringBuilder result = new StringBuilder();
		if (file.getType().equals(FilesType.Binary)) {
			BinaryFile binaryFile = (BinaryFile) file;
			for (int i = 0; i < binaryFile.getSize(); i++) {
				result.append(binaryFile.getContentByIndex(i) + " ");
			}
			result.append("\n");
		}

		return result.toString();
	}
}
