package sg.edu.nus.iss.app.VTTPaddressbook;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static sg.edu.nus.iss.app.VTTPaddressbook.util.IOutil.*;

@SpringBootApplication
public class VttPaddressbookApplication {
	private static final Logger logger = Logger.getLogger(VttPaddressbookApplication.class.getName());
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(VttPaddressbookApplication.class);
		DefaultApplicationArguments appArgs = new DefaultApplicationArguments(args);
		List<String> optVals = appArgs.getOptionValues("dataDir");
		if(optVals!=null){
			createDir((String)optVals.get(0));
			logger.log(Level.INFO, "Created directory successfully");
		}else{
			logger.log(Level.WARNING, "No directory supplied");
			System.exit(1);
		}
		//SpringApplication.run(VttPaddressbookApplication.class, args);
		app.run(args);
	}

}
