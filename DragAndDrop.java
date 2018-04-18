import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class DragAndDrop extends JPanel implements MouseListener, MouseMotionListener {

    int a=0;
    int b=0;
    int c=0;
    int d=0;
    int q=0;
    int w=0;
    int change = -1;

    public ArrayList<Selection> log = new ArrayList<Selection>();

    public static int foodindex = -1;
    public static int itemindex = -1;
    public static int charmindex = -1;
    public static int picshown = 0;

    static String[] foods = {" ","sandwich","scorn","bagel","pie"};
    static String[] items = {" ","bowl","lamp","towel","tent"};
    static String[] charms = {" ","lucky clover","lucky bell","amulet"};
    static Image[] foodsimages = {
      new ImageIcon("sandwich.png").getImage(),
      new ImageIcon("scorn.png").getImage(),
      new ImageIcon("bagel.png").getImage(),
      new ImageIcon("pie.png").getImage(),
    };
    static Image[] itemsimages = {
      new ImageIcon("bowl.png").getImage(),
      new ImageIcon("lamp.png").getImage(),
      new ImageIcon("towel.png").getImage(),
      new ImageIcon("tent.png").getImage(),
    };
    static Image[] charmsimages = {
      new ImageIcon("clover.png").getImage(),
      new ImageIcon("bell.png").getImage(),
      new ImageIcon("amulet.png").getImage(),
    };

    static Image[] pics = {
      new ImageIcon("back.png").getImage(),
      new ImageIcon("bowlp.png").getImage(),
      new ImageIcon("lampp.png").getImage(),
      new ImageIcon("towelp.png").getImage(),
      new ImageIcon("tentp.png").getImage(),
      new ImageIcon("r1.png").getImage(),
      new ImageIcon("r2.png").getImage(),
      new ImageIcon("r3.png").getImage(),
      new ImageIcon("r4.png").getImage(),
      new ImageIcon("r5.png").getImage(),
      new ImageIcon("r6.png").getImage(),
      new ImageIcon("r7.png").getImage(),
      new ImageIcon("r8.png").getImage(),
      new ImageIcon("r9.png").getImage(),
      new ImageIcon("r10.png").getImage(),
      new ImageIcon("ssr1.png").getImage(),
      new ImageIcon("ssr2.png").getImage(),
    };


    public static void main(String[] args) {
        JFrame window = new JFrame("DragAndDrop");
        JPanel content = new DragAndDrop();


        window.setContentPane(content);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocation(120,70);
        window.setSize(1000,600);
        window.setVisible(true);
    }


    public DragAndDrop(){

      addMouseListener(this);
      addMouseMotionListener(this);


      setLayout(new FlowLayout());
      JPanel selectboxes = new JPanel();
      selectboxes.setPreferredSize(new Dimension(250,400));
      selectboxes.setLayout(new GridLayout(0,1));

      JComboBox foodList = new JComboBox(foods);
      JLabel foodLabel = new JLabel("Select food:");
      selectboxes.add(foodLabel);
      selectboxes.add(foodList);

      JComboBox itemList = new JComboBox(items);
      JLabel itemLabel = new JLabel("Select item:");
      selectboxes.add(itemLabel);
      selectboxes.add(itemList);

      JComboBox charmList = new JComboBox(charms);
      JLabel charmLabel = new JLabel("Select charm:");
      selectboxes.add(charmLabel);
      selectboxes.add(charmList);

      JButton idk = new JButton("I don't know what to select!");
      JButton finSelect = new JButton("終わった   Done seleciton!");
      selectboxes.add(finSelect);
      selectboxes.add(idk);
      add(selectboxes);


      JLabel piclabel = new JLabel(new ImageIcon(new ImageIcon("trans.png").getImage().getScaledInstance(550,350, Image.SCALE_DEFAULT)));
      add(piclabel);

      JPanel buttonpad = new JPanel();
      selectboxes.setPreferredSize(new Dimension(250,500));
      buttonpad.setLayout(new GridLayout(0,1));
      JButton travel = new JButton("行ってらっしゃい   Have fun!");
      JButton clear = new JButton("Clear");
      JTextArea result = new JTextArea("\n\n\n");
      buttonpad.add(travel);
      buttonpad.add(result);
      buttonpad.add(clear);
      add(buttonpad);

      finSelect.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            String foodselected = (String)foodList.getSelectedItem();
            String itemselected = (String)itemList.getSelectedItem();
            String charmselected = (String)charmList.getSelectedItem();
            foodindex = returnIndex(foods,foodselected);
            itemindex = returnIndex(items,itemselected);
            charmindex = returnIndex(charms,charmselected);
            Selection newSelection = new Selection(foodindex,itemindex,charmindex);
            log.add(newSelection);
            repaint();
        }
      });

      idk.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            int lastfoodindex = log.get(log.size()-1).foodindex;
            int lastitemindex = log.get(log.size()-1).itemindex;
            int lastcharmindex = log.get(log.size()-1).charmindex;
            foodList.setSelectedItem(foods[lastfoodindex]);
            itemList.setSelectedItem(items[lastitemindex]);
            charmList.setSelectedItem(charms[lastcharmindex]);
            repaint();
        }
      });

      travel.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            int random = (int) Math.ceil(Math.random() * 100);
            if (random<30){
              if (itemindex==1){
                picshown = 1;
              } else if(itemindex==2){
                picshown = 2;
              } else if (itemindex==3){
                picshown = 3;
              } else if (itemindex==4){
                picshown = 4;
              }
            } else if (random>90 && random<=95){
              picshown = 15;
            } else if (random>95 && random<=100){
              picshown = 16;
            } else {
              int random2 = (int) Math.ceil(Math.random() * 10);
              picshown = random2+4;
            }
            a = 0;
            b = 0;
            c = 0;
            d = 0;
            q = 0;
            w = 0;
            repaint();
            result.setText("\n写真がとどいています \n Look at the photo I took!\n");
            foodindex = -1;
            itemindex = -1;
            charmindex = -1;
        }
      });

      clear.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            picshown = 0;
            repaint();
            result.setText("\n\n\n");
            foodList.setSelectedItem(foods[0]);
            itemList.setSelectedItem(items[0]);
            charmList.setSelectedItem(charms[0]);
        }
      });


    }

    public static int returnIndex(String[] list,String keyword){
      int result = -1;
      for (int i=0;i<list.length;i++){
        if (list[i]==keyword){
          result = i;
        }
      }
      return result;
    }


		 public void paintComponent(Graphics g){
          super.paintComponent(g);
          Image image1 = pics[picshown];
          g.drawImage(image1,320,120,null);
          if (foodindex!=-1){
            Image image2 = foodsimages[foodindex-1];
            g.drawImage(image2,350+a,20+b,null);
          }
          if (itemindex!=-1){
            Image image3 = itemsimages[itemindex-1];
            g.drawImage(image3,450+c,20+d,null);
          }
          if (charmindex!=-1){
            Image image4 = charmsimages[charmindex-1];
            g.drawImage(image4,550+q,20+w,null);
          }
			 }


    public void mousePressed(MouseEvent evt) {
      int cordx = evt.getX();
      int cordy = evt.getY();
      if (10<cordy && cordy<110) {
        if (350<cordx && cordx<450){
          change = 1;
        } else if (450<cordx && cordx<550){
          change = 2;
        } else if (550<cordx && cordx<650){
          change = 3;
        }
      }
    }
		public void mouseEntered(MouseEvent evt) { }
		public void mouseExited(MouseEvent evt) { }
		public void mouseClicked(MouseEvent evt) { }
		public void mouseReleased(MouseEvent evt) { }
		public void mouseMoved(MouseEvent evt){ }

		public void mouseDragged(MouseEvent evt){
      int cordx = evt.getX();
      int cordy = evt.getY();
      if (change==1){
        a = cordx-350-50;
        b = cordy-20-50;
      } else if (change==2){
        c = cordx-450-50;
        d = cordy-20-50;
      } else if (change==3){
        q = cordx-550-50;
        w = cordy-20-50;
      }
      repaint();
		}


}
