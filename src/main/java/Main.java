public class Main {

   public static void main (String args[]) {
      String filepath = "";
      String viewType = "";
      String outputType = "";
      int speed = 1;

//      IModel model;
//      IView view;
//      IController controller;
      Appendable out = new StringBuffer();

      String parsedArgs;
      int i = 0;
      if (args.length > 0) {
         while (i < args.length) {
            parsedArgs = args[i];

            if (parsedArgs.equals("-if")) {
               filepath = args[i + 1];
            }
            if (parsedArgs.equals("-iv")) {
               viewType = args[i + 1];
            }
            if (parsedArgs.equals("-o")) {
               outputType = args[i + 1];
            }
            if (parsedArgs.equals("-speed")) {
               speed = Integer.parseInt(args[i + 1]);
            }
            i++;
         }
      }
   }
}
