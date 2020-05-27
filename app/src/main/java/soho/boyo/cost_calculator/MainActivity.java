package soho.boyo.cost_calculator;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import soho.boyo.cost_calculator.Calculate.Calculate;

public class MainActivity extends AppCompatActivity {

    static final String data = "data";
    static final String ground_cost = "ground_cost";
    static final String ball_cost_float = "ball_cost_float";
    static final String now_pub_money = "now_pub_money";
    static final String next_ground_money = "next_ground_money";
    static final String card_money = "card_money";
    final int simple = 0;
    final int force = 1;
    EditText et1, et2, et3, et4, et5, et6, et7, et8, et9, et10, et11, et12, et13;
    Button bt, bt_clear;
    TextView tv, tv1_1, tv1_2,
            tv2_1, tv2_2, tv2_3, tv2_4,
            tv3_1, tv3_2, tv3_3,
            tv4_1,
            tv5_1,
            tv_detail;
    TextView tv6, tv7, tv8, tv9, tv10, tv11, tv12, tv13;
    LinearLayout LL1, LL2, LL3, LL4, LL5;
    String text_string = "填入相對應顏色之欄位即可進行計算\n" +
            "可單純計算場地費或球費\n" +
            "公費(橘色) 及 場地基金(紫色) 欄位為選填項目";
    SharedPreferences Preferences;
    int status = 0;
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Init();
        SP();

    }

    void start_calculator() {
        if (et5.getText().toString().equals("") ||
                Float.parseFloat(et5.getText().toString()) == 0) {
            showToast("請確認欄位 - [打球人數]");
            text_string = "請確認欄位 - [打球人數]";
            tv.setText(text_string);
            myClosekeyboard();
            return;
        }

        text_string = "----------\n";
        float total_cost_ground = 0;
        float total_cost_ball = 0;

        //  場地費區塊
        int groundCostPerHour = et1.getText().toString().equals("") ? 0 : Integer.parseInt(et1.getText().toString());
        float hour = et2.getText().toString().equals("") ? 0 : Float.parseFloat(et2.getText().toString());
        text_string += Calculate.getGroundCost(groundCostPerHour, hour);

        total_cost_ground = Calculate.groundCost(groundCostPerHour, hour);

        if (groundCostPerHour > 0)
            Preferences.edit().putInt(ground_cost, groundCostPerHour).commit();

        //  球費區塊
        float ballCostEach = et3.getText().toString().equals("") ? 0 : Float.parseFloat(et3.getText().toString());
        int number = et4.getText().toString().equals("") ? 0 : Integer.parseInt(et4.getText().toString());
        text_string += Calculate.getBallCost(ballCostEach, number);

        total_cost_ball = Calculate.ballCost(ballCostEach, number);
        if (ballCostEach > 0)
            Preferences.edit().putFloat(ball_cost_float, ballCostEach).commit();

        //  ↓↓↓    明細區塊   ↓↓↓
        text_string += Calculate.getTotalCost(total_cost_ground, total_cost_ball);

        boolean b = total_cost_ground > 0 && total_cost_ball > 0 ? true : total_cost_ground > 0 || total_cost_ball > 0;
        if (!b) {
            showToast("請完成相對應顏色之欄位");
            text_string = "請完成相對應顏色之欄位";
            tv.setText(text_string);
            myClosekeyboard();
            return;
        }

        float total_cost = total_cost_ground + total_cost_ball;
        float people = Float.parseFloat(et5.getText().toString());
        float average = total_cost / people;
        int each_cost = Calculate.getEachPay((int) average);
        text_string += Calculate.getAverage(total_cost, people, (int) average);
        //  ↑↑↑    明細區塊   ↑↑↑

        float total_pub_money = 0;
        float in_money = 0;
        float today_money = 0;
        float _next_ground_money = 0;
        float reduce_next_ground_money = 0;
        float today_next_ground_money = 0;
        float now_next_ground_money = 0;

        int money = et7.getText().toString().equals("") ? 0 : Integer.parseInt(et7.getText().toString());
        text_string += money > 0 ? Calculate.getPublicExpense(people, money, total_cost) : "";

        /*if (!et6.getText().toString().equals("") && !et7.getText().toString().equals("") && Integer.parseInt(et7.getText().toString()) > 0 && total_cost > 0) {
            int pub_money = Integer.parseInt(et6.getText().toString());
            today_money = (Integer.parseInt(et7.getText().toString()) * Integer.parseInt(et5.getText().toString())) - total_cost;
            total_pub_money = pub_money + today_money;
            in_money = Integer.parseInt(et7.getText().toString()) * Integer.parseInt(et5.getText().toString());
            text_string += "每人收費 x 人數 = 今日總收金額\n" +
                    et7.getText().toString() + " x " + et5.getText().toString() + " = " + String.valueOf(in_money) + "\n" +
                    "今日總收金額 - 今日總支出 = 今日公費\n" +
                    in_money + " - " + total_cost + " = " + today_money + "\n";
            if (!et13.getText().toString().equals("")) {
                total_pub_money = pub_money + today_money - Integer.parseInt(et13.getText().toString());
                text_string += "累積公費 + 今日公費 - 公費支出 = 當前總公費\n" +
                        String.valueOf(pub_money) + " + " + String.valueOf(today_money) + " - " + et13.getText().toString() + " = " + String.valueOf((total_pub_money)) + "\n" +
                        "----------\n";
            } else {
                text_string += "累積公費 + 今日公費 = 當前總公費\n" +
                        String.valueOf(pub_money) + " + " + String.valueOf(today_money) + " = " + String.valueOf((total_pub_money)) + "\n" +
                        "----------\n";
            }
            Preferences.edit().putInt(now_pub_money, (int) total_pub_money).commit();

            if (status == force) {
                if (!et8.getText().toString().equals("") && !et9.getText().toString().equals("") && Integer.parseInt(et8.getText().toString()) > 0 && Integer.parseInt(et9.getText().toString()) >= 0) {
                    _next_ground_money = Integer.parseInt(et8.getText().toString());
                    reduce_next_ground_money = Integer.parseInt(et9.getText().toString());
                    today_next_ground_money = in_money - today_money - total_cost_ball - reduce_next_ground_money;
                    now_next_ground_money = _next_ground_money + today_next_ground_money;
                    if (!et12.getText().toString().equals("")) {
                        now_next_ground_money = _next_ground_money + today_next_ground_money + Integer.parseInt(et12.getText().toString());
                        text_string += "總收入 - 公費 - 球費 - 折抵 = 今日場地費基金\n" +
                                in_money + " - " + today_money + " - " + total_cost_ball + " - " + reduce_next_ground_money + " = " + today_next_ground_money + "\n" +
                                "累積場地基金 + 今日場地費基金 + 練習場地 = 場地費基金\n" +
                                et8.getText().toString() + " + " + today_next_ground_money + " + " + et12.getText().toString() + " = " + now_next_ground_money;
                    } else {
                        text_string += "總收入 - 公費 - 球費 - 折抵 = 今日場地費基金\n" +
                                in_money + " - " + today_money + " - " + total_cost_ball + " - " + reduce_next_ground_money + " = " + today_next_ground_money + "\n" +
                                "累積場地基金 + 今日場地費基金 = 場地費基金\n" +
                                et8.getText().toString() + " + " + today_next_ground_money + " = " + now_next_ground_money;
                    }
                    Preferences.edit().putInt(next_ground_money, (int) now_next_ground_money).commit();
                }
            }
        }*/

        float _card_money = 0;
        if (!et10.getText().toString().equals("") && Integer.parseInt(et10.getText().toString()) > 0) {
            if (!et11.getText().toString().equals(""))
                _card_money = Integer.parseInt(et10.getText().toString()) - total_cost_ground - Integer.parseInt(et11.getText().toString());
            else
                _card_money = Integer.parseInt(et10.getText().toString()) - total_cost_ground;
            Preferences.edit().putInt(card_money, (int) _card_money).commit();
        }

        show_result(average, each_cost,
                in_money, total_cost, today_money, total_pub_money,
                _next_ground_money, reduce_next_ground_money, today_next_ground_money, now_next_ground_money,
                _card_money,
                total_cost_ball);
    }

    private void show_result(float average, int each_cost,
                             float in_money, float total_cost, float today_money, float total_pub_money,
                             float _next_ground_money, float reduce_next_ground_money, float today_next_ground_money, float now_next_ground_money,
                             float _card_money,
                             float total_cost_ball) {
        tv.setText("");
        tv.setVisibility(View.GONE);

        LL1.setVisibility(View.VISIBLE);
        tv1_1.setText(String.valueOf(average));
        tv1_2.setText(String.valueOf(each_cost));

        if (in_money != 0) {
            LL2.setVisibility(View.VISIBLE);
            tv2_1.setText(String.valueOf(in_money));
            tv2_2.setText(String.valueOf(total_cost));
            tv2_3.setText(String.valueOf(today_money));
            tv2_4.setText(String.valueOf(total_pub_money));

            if (status == force) {
                LL5.setVisibility(View.VISIBLE);
                tv5_1.setText(String.valueOf(total_cost_ball));
            }
        } else {
            LL2.setVisibility(View.GONE);
            LL5.setVisibility(View.GONE);
        }

        if (_next_ground_money != 0 && reduce_next_ground_money >= 0 && in_money != 0 && status == force) {
            LL3.setVisibility(View.VISIBLE);
            tv3_1.setText(String.valueOf(_next_ground_money));
            tv3_2.setText(String.valueOf(today_next_ground_money));
            tv3_3.setText(String.valueOf(now_next_ground_money));
        } else
            LL3.setVisibility(View.GONE);

        if (_card_money != 0 && status == force) {
            LL4.setVisibility(View.VISIBLE);
            tv4_1.setText(String.valueOf(_card_money));
        } else
            LL4.setVisibility(View.GONE);

        tv_detail.setVisibility(View.VISIBLE);
        tv_detail.setText("點擊我看明細");
        tv_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("明細")
                        .setMessage(text_string)
                        .setPositiveButton("返回", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                return;
                            }
                        })
                        .show();
                return;
            }
        });
        myClosekeyboard();
    }

    private void Init() {
        LL1 = (LinearLayout) findViewById(R.id.LL1);
        LL2 = (LinearLayout) findViewById(R.id.LL2);
        LL3 = (LinearLayout) findViewById(R.id.LL3);
        LL4 = (LinearLayout) findViewById(R.id.LL4);
        LL5 = (LinearLayout) findViewById(R.id.LL5);

        tv6 = (TextView) findViewById(R.id.textView6);
        tv7 = (TextView) findViewById(R.id.textView7);
        tv8 = (TextView) findViewById(R.id.textView8);
        tv9 = (TextView) findViewById(R.id.textView9);
        tv10 = (TextView) findViewById(R.id.textView10);
        tv11 = (TextView) findViewById(R.id.textView11);
        tv12 = (TextView) findViewById(R.id.textView12);
        tv13 = (TextView) findViewById(R.id.textView13);

        et1 = (EditText) findViewById(R.id.editText1);
        et2 = (EditText) findViewById(R.id.editText2);
        et3 = (EditText) findViewById(R.id.editText3);
        et4 = (EditText) findViewById(R.id.editText4);
        et5 = (EditText) findViewById(R.id.editText5);
        et6 = (EditText) findViewById(R.id.editText6);
        et7 = (EditText) findViewById(R.id.editText7);
        et8 = (EditText) findViewById(R.id.editText8);
        et9 = (EditText) findViewById(R.id.editText9);
        et10 = (EditText) findViewById(R.id.editText10);
        et11 = (EditText) findViewById(R.id.editText11);
        et12 = (EditText) findViewById(R.id.editText12);
        et13 = (EditText) findViewById(R.id.editText13);
        bt = (Button) findViewById(R.id.button1);
        bt_clear = (Button) findViewById(R.id.button2);
        tv = (TextView) findViewById(R.id.textView);
        tv_detail = (TextView) findViewById(R.id.tv_detail);

        tv1_1 = (TextView) findViewById(R.id.tv1_1);
        tv1_2 = (TextView) findViewById(R.id.tv1_2);
        tv2_1 = (TextView) findViewById(R.id.tv2_1);
        tv2_2 = (TextView) findViewById(R.id.tv2_2);
        tv2_3 = (TextView) findViewById(R.id.tv2_3);
        tv2_4 = (TextView) findViewById(R.id.tv2_4);
        tv3_1 = (TextView) findViewById(R.id.tv3_1);
        tv3_2 = (TextView) findViewById(R.id.tv3_2);
        tv3_3 = (TextView) findViewById(R.id.tv3_3);
        tv4_1 = (TextView) findViewById(R.id.tv4_1);
        tv5_1 = (TextView) findViewById(R.id.tv5_1);

        tv.setText(text_string);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_calculator();
                return;
            }
        });

        bt_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et1.setText("");
                et2.setText("");
                et3.setText("");
                et4.setText("");
                et5.setText("");
                et6.setText("");
                et7.setText("");
                et8.setText("");
                et9.setText("");
                et10.setText("");
                et11.setText("");
                et12.setText("");
                et13.setText("");
                return;
            }
        });

        //tv6.setVisibility(View.GONE);
        //tv7.setVisibility(View.GONE);
        tv8.setVisibility(View.GONE);
        tv9.setVisibility(View.GONE);
        tv10.setVisibility(View.GONE);
        tv11.setVisibility(View.GONE);
        tv12.setVisibility(View.GONE);
        //tv13.setVisibility(View.GONE);
        //et6.setVisibility(View.GONE);
        //et7.setVisibility(View.GONE);
        et8.setVisibility(View.GONE);
        et9.setVisibility(View.GONE);
        et10.setVisibility(View.GONE);
        et11.setVisibility(View.GONE);
        et12.setVisibility(View.GONE);
        //et13.setVisibility(View.GONE);
    }

    private void SP() {
        Preferences = getSharedPreferences(data, 0);
        if (Preferences.getInt(ground_cost, 0) != 0)
            et1.setText(String.valueOf(Preferences.getInt(ground_cost, 0)));
        if (Preferences.getFloat(ball_cost_float, 0) != 0)
            et3.setText(String.valueOf(Preferences.getFloat(ball_cost_float, 0)));
        if (Preferences.getInt(now_pub_money, 0) != 0)
            et6.setText(String.valueOf(Preferences.getInt(now_pub_money, 0)));
        if (Preferences.getInt(next_ground_money, 0) != 0)
            et8.setText(String.valueOf(Preferences.getInt(next_ground_money, 0)));
        if (Preferences.getInt(card_money, 0) != 0)
            et10.setText(String.valueOf(Preferences.getInt(card_money, 0)));
        et2.requestFocus();
    }

    private void showToast(String text) {
        if (mToast == null) {
            mToast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    private void myClosekeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("準備離開本程式?")
                    .setPositiveButton("否", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            return;
                        }
                    })
                    .setNegativeButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MainActivity.this.finish();
                        }
                    })
                    .show();
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 0, Menu.NONE, "切換");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (status) {
            case simple:
                //tv6.setVisibility(View.VISIBLE);
                //tv7.setVisibility(View.VISIBLE);
                tv8.setVisibility(View.VISIBLE);
                tv9.setVisibility(View.VISIBLE);
                tv10.setVisibility(View.VISIBLE);
                tv11.setVisibility(View.VISIBLE);
                tv12.setVisibility(View.VISIBLE);
                //et6.setVisibility(View.VISIBLE);
                //et7.setVisibility(View.VISIBLE);
                et8.setVisibility(View.VISIBLE);
                et9.setVisibility(View.VISIBLE);
                et10.setVisibility(View.VISIBLE);
                et11.setVisibility(View.VISIBLE);
                et12.setVisibility(View.VISIBLE);
                status = force;
                break;
            case force:
                //tv6.setVisibility(View.GONE);
                //tv7.setVisibility(View.GONE);
                tv8.setVisibility(View.GONE);
                tv9.setVisibility(View.GONE);
                tv10.setVisibility(View.GONE);
                tv11.setVisibility(View.GONE);
                tv12.setVisibility(View.GONE);
                //et6.setVisibility(View.GONE);
                //et7.setVisibility(View.GONE);
                et8.setVisibility(View.GONE);
                et9.setVisibility(View.GONE);
                et10.setVisibility(View.GONE);
                et11.setVisibility(View.GONE);
                et12.setVisibility(View.GONE);
                status = simple;
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
