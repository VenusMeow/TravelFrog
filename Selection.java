public class Selection{
  int foodindex;
  int itemindex;
  int charmindex;

  String[] foods = {" ","sandwich","scorn","bagel","pie"};
  String[] items = {" ","bowl","lamp","towel","tent"};
  String[] charms = {" ","lucky clover","lucky bell","amulet"};

  public Selection(int f,int i, int c){
    this.foodindex = f;
    this.itemindex = i;
    this.charmindex = c;
  }

  public String toString(){
    return "The last selection is " + foods[foodindex] + ", " + items[itemindex] + ", and " + charms[charmindex] + ". ";
  }
}
