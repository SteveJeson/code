package wind;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    //报警按钮
    List<Button> btnList = new ArrayList<>();

    //车辆状态按钮
    List<Button> btnList2 = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("ZDZC");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label alarmStatus = new Label("报警标志位:");
        grid.add(alarmStatus, 0, 1);

        TextField alarmTextField = new TextField();
        grid.add(alarmTextField, 1, 1);

        Label vehicleStatus = new Label("车辆状态位:");
        grid.add(vehicleStatus, 0, 2);

        TextField vehicleTextField = new TextField();
        grid.add(vehicleTextField, 1, 2);

        alarmTextField.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    btnList.stream().forEach(bt -> {
                        grid.getChildren().remove(bt);
                    });
                    int btnCount = 5;
                    String alarmStatus = ByteUtil.get32BitBinStrFromInt(Integer.valueOf(newValue,16));//100 20000000  20000100
                    alarmStatus = new StringBuffer(alarmStatus).reverse().toString();

                    for(int i = 0;i < alarmStatus.length();i++){
                        String str = alarmStatus.substring(i, i +1);
                        switch (i){
                            case 5:
                                if("1".equals(str)){
                                    System.out.println(btnCount);
                                    Button btn = new Button(AlarmType.GNSSNOTCONNECT.getDesc());
                                    btn.setPrefWidth(200);
                                    btn.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
                                    grid.add(btn, 0, btnCount);
                                    if(!btnList.contains(btn)){
                                        btnList.add(btn);
                                    }
                                    btnCount++;
                                }
                                break;
                            case 6:
                                if("1".equals(str)){
                                    System.out.println(btnCount);
                                    Button btn = new Button(AlarmType.GNSSCIRCUIT.getDesc());
                                    btn.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
                                    grid.add(btn, 0, btnCount);
                                    if(!btnList.contains(btn)){
                                        btnList.add(btn);
                                    }
                                    btnCount++;
                                }
                                break;
                            case 7:
                                if("1".equals(str)){
                                    System.out.println(btnCount);
                                    Button btn = new Button(AlarmType.UNDERVOLTAGE.getDesc());
                                    btn.setPrefWidth(200);
                                    btn.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
                                    grid.add(btn, 0, btnCount);
                                    if(!btnList.contains(btn)){
                                        btnList.add(btn);
                                    }
                                    btnCount++;
                                }
                                break;

                            case 8:
                                if("1".equals(str)){
                                    System.out.println(btnCount);
                                    Button btn = new Button(AlarmType.POWERDOWN.getDesc());
                                    btn.setPrefWidth(200);
                                    btn.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
                                    grid.add(btn, 0, btnCount);
                                    if(!btnList.contains(btn)){
                                        btnList.add(btn);
                                    }
                                    btnCount++;
                                }
                                break;
                            case 28:
                                if("1".equals(str)){
                                    System.out.println(btnCount);
                                    Button btn = new Button(AlarmType.UNLAWOFFSET.getDesc());
                                    btn.setPrefWidth(200);
                                    btn.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
                                    grid.add(btn, 0, btnCount);
                                    if(!btnList.contains(btn)){
                                        btnList.add(btn);
                                    }
                                    btnCount++;
                                }
                                break;
                            case 29:
                                if("1".equals(str)){
                                    System.out.println(btnCount);
                                    Button btn = new Button(AlarmType.SHAKE.getDesc());
                                    btn.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
                                    grid.add(btn, 0, btnCount);
                                    if(!btnList.contains(btn)){
                                        btnList.add(btn);
                                    }
                                    btnCount++;
                                }
                                break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        vehicleTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    btnList2.stream().forEach(bt -> {
                        grid.getChildren().remove(bt);
                    });
                    int btnCount = 5;
                    String bst = ByteUtil.get32BitBinStrFromInt(Integer.parseInt(newValue));
                    bst = new StringBuffer(bst).toString();//反转字符串，以 低位-》高位的顺序读取解析状态位

                    for (int i = 0; i < bst.length(); i++) {
                        String str = bst.substring(i, i +1);
                        switch (i){
                            case 0:
                                if("1".equals(str)){
                                    Button btn = new Button("ACC 开");
                                    btn.setBackground(new Background(new BackgroundFill(Color.GREEN,null,null)));
                                    grid.add(btn, 1, btnCount);
                                    if(!btnList2.contains(btn)){
                                        btnList2.add(btn);
                                    }
                                    btnCount++;
                                }else{
                                    Button btn = new Button("ACC 关");
                                    btn.setBackground(new Background(new BackgroundFill(Color.GREEN,null,null)));
                                    grid.add(btn, 1, btnCount);
                                    if(!btnList2.contains(btn)){
                                        btnList2.add(btn);
                                    }
                                    btnCount++;
                                }
                                break;
                            case 1:
                                if("1".equals(str)){
                                    Button btn = new Button("已定位");
                                    btn.setBackground(new Background(new BackgroundFill(Color.GREEN,null,null)));
                                    grid.add(btn, 1, btnCount);
                                    if(!btnList2.contains(btn)){
                                        btnList2.add(btn);
                                    }
                                    btnCount++;
                                }else{
                                    Button btn = new Button("未定位");
                                    btn.setBackground(new Background(new BackgroundFill(Color.GREEN,null,null)));
                                    grid.add(btn, 1, btnCount);
                                    if(!btnList2.contains(btn)){
                                        btnList2.add(btn);
                                    }
                                    btnCount++;
                                }
                            break;
                            case 10:
                                if("1".equals(str)){
                                    Button btn = new Button("车辆油路断开");
                                    btn.setBackground(new Background(new BackgroundFill(Color.GREEN,null,null)));
                                    grid.add(btn, 1, btnCount);
                                    if(!btnList2.contains(btn)){
                                        btnList2.add(btn);
                                    }
                                    btnCount++;
                                }else{
                                    Button btn = new Button("车辆油路正常");
                                    btn.setBackground(new Background(new BackgroundFill(Color.GREEN,null,null)));
                                    grid.add(btn, 1, btnCount);
                                    if(!btnList2.contains(btn)){
                                        btnList2.add(btn);
                                    }
                                    btnCount++;
                                }
                                break;
                            case 11:
                                if("1".equals(str)){
                                    Button btn = new Button("车辆电路断开");
                                    btn.setBackground(new Background(new BackgroundFill(Color.GREEN,null,null)));
                                    grid.add(btn, 1, btnCount);
                                    if(!btnList2.contains(btn)){
                                        btnList2.add(btn);
                                    }
                                    btnCount++;
                                }else{
                                    Button btn = new Button("车辆电路正常");
                                    btn.setBackground(new Background(new BackgroundFill(Color.GREEN,null,null)));
                                    grid.add(btn, 1, btnCount);
                                    if(!btnList2.contains(btn)){
                                        btnList2.add(btn);
                                    }
                                    btnCount++;
                                }
                            break;

                            case 22:
                                if("1".equals(str)){
                                    Button btn = new Button("已设防");
                                    btn.setBackground(new Background(new BackgroundFill(Color.GREEN,null,null)));
                                    grid.add(btn, 1, btnCount);
                                    if(!btnList2.contains(btn)){
                                        btnList2.add(btn);
                                    }
                                    btnCount++;
                                }else{
                                    Button btn = new Button("已撤防");
                                    btn.setBackground(new Background(new BackgroundFill(Color.GREEN,null,null)));
                                    grid.add(btn, 1, btnCount);
                                    if(!btnList2.contains(btn)){
                                        btnList2.add(btn);
                                    }
                                    btnCount++;
                                }
                            break;
                            default:
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        Application.launch(args);
    }


}
