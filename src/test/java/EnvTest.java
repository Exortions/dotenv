import io.github.exortions.dotenv.DotEnv;
import io.github.exortions.dotenv.EnvParameterNotFoundException;

import java.io.File;

public class EnvTest {

    public static void main(String[] args) {
        DotEnv env = new DotEnv(new File(".env"));
        env.loadParams();

        try {
            System.out.println(env.getParameter("token"));
            System.out.println(env.getParameter("password"));
        } catch (EnvParameterNotFoundException e) {
            e.printStackTrace();
        }
    }

}
