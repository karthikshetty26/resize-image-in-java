//	public String resizeImage(MultipartFile multipartFile) throws IOException{
//  public String resizeImage(File inputFile) throws IOException{
	public static void main(String[] args) throws IOException {

		// Below 3 comments are for extracting file-name and file-extension from MultipartFile type.

		// String fileExtension = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1);
		// String fileName = multipartFile.getOriginalFilename().substring(0, multipartFile.getOriginalFilename().lastIndexOf("."));
		// fileExtension = fileExtension.toLowerCase();

		// Below code if for converting multipartFile to File type.
		// File inputFile = new File(multipartFile.getOriginalFilename());
		// FileCopyUtils.copy(multipartFile.getBytes(), inputFile);

		// If you have image in File form you can skip above steps and continue from below.
		File inputFile = new File("input.jpg");

		// Below 3 lines are for extracting file name file extension from MultipartFile.

		String fileExtension = inputFile.getName().substring(inputFile.getName().lastIndexOf(".") + 1);
		String fileName = inputFile.getName().substring(0, inputFile.getName().lastIndexOf("."));
		fileExtension = fileExtension.toLowerCase();

		System.out.print("File extention" + fileExtension);

		BufferedImage inputImage = ImageIO.read(inputFile);

		// Set your desired size below.
		int maxSize = 1200;

		int width = inputImage.getWidth();
		int height = inputImage.getHeight();
		if (width > maxSize || height > maxSize) {

			double scaleRatio = Math.min((double) maxSize / width, (double) maxSize / height);

			int targetWidth = (int) (width * scaleRatio);
			int targetHeight = (int) (height * scaleRatio);

			BufferedImage scaledImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);

			Graphics2D graphics2D = scaledImage.createGraphics();
			graphics2D.drawImage(inputImage, 0, 0, targetWidth, targetHeight, null);
			graphics2D.dispose();

			File outputFile = new File(fileName + "." + fileExtension);
			ImageIO.write(scaledImage, fileExtension, outputFile);

			System.out.println("Image scaled successfully.");

			// outputFile will have the resized image in File type.
		}
	}
