public class forLoops {
    public static void main(String[] args) {

System.out.println("DOES THIS WORK?");
        for(int i=0; i<4; i++){
System.out.println("This will repeat 4 times ... hopefully!");
            if(i==2){
System.out.println("Here is something in-between!!!");
            }
        }
        for(int a=0; a<4; a++){
System.out.println("Now this will repeat 4 times... probably!!!");
        }
        for(int s=0; s<4; s++){
System.out.println("HELP!!!");
          for(int j=0; j<4; j++){
System.out.println("Can't forget about nested loops! This will repeat a bunch times");
            for(int k=0; k<4; k++){
System.out.println("I hope you like this sentence, it'll appear a lot!!!");
              if(k==2){
System.out.println("Don't worry you're almost done!!!");
              }
            }
          }
        }
      }
}
