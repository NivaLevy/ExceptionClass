package com.example.niva.exceptionclass;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private BookStore store;
    private Button cmdAdd, cmdPrint, cmdAuthor, cmdBuy;
    private TextView list;
    String userInputValue = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        store = new BookStore();
        list = (TextView)findViewById(R.id.list);
        cmdAdd = (Button)findViewById(R.id.cmdAdd);
        cmdAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LinearLayout layout = new LinearLayout(v.getContext());
                layout.setOrientation(LinearLayout.VERTICAL);

                final EditText titleBox = new EditText(v.getContext());
                titleBox.setHint("Book");
                layout.addView(titleBox);

                final EditText authorBox = new EditText(v.getContext());
                authorBox.setHint("Author");
                layout.addView(authorBox);

                final AlertDialog.Builder inputAlert = new AlertDialog.Builder(v.getContext());
                inputAlert.setTitle("Title of the Input Box");
                inputAlert.setMessage("Enter name of book and author");

                inputAlert.setView(layout);
                inputAlert.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        store.add(new Book(titleBox.getText().toString() , authorBox.getText().toString()));
                    }
                });
                inputAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = inputAlert.create();
                alertDialog.show();


            }
        });

        cmdPrint = (Button)findViewById(R.id.cmdPrint);
        cmdPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.setText(store.toString());
            }
        });

        cmdAuthor = (Button)findViewById(R.id.cmdAuthor);
        cmdAuthor.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final AlertDialog.Builder inputAlert = new AlertDialog.Builder(v.getContext());
                inputAlert.setTitle("Title of the Input Box");
                inputAlert.setMessage("Enter name of author");
                final EditText userInput = new EditText(v.getContext());
                inputAlert.setView(userInput);
                inputAlert.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userInputValue= userInput.getText().toString();
                        try {
                            Book[] arr = store.booksByAuthor(userInputValue);
                            String str = "";
                            for (Book s: arr) {
                                if(s == null)
                                    break;
                                str += s + "\n";
                            }
                            list.setText(str);
                        } catch (AuthorNotFoundException e) {
                            Toast.makeText(getApplicationContext(),"No such author.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                inputAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = inputAlert.create();
                alertDialog.show();



            }
        });
        cmdBuy = (Button)findViewById(R.id.cmdBuy);
        cmdBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder inputAlert = new AlertDialog.Builder(v.getContext());
                inputAlert.setTitle("Title of the Input Box");
                inputAlert.setMessage("Enter name of book to buy");
                final EditText userInput = new EditText(v.getContext());
                inputAlert.setView(userInput);
                inputAlert.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            store.buy(userInput.getText().toString());
                        } catch (BookNotFoundException e) {
                            Toast.makeText(getApplicationContext(), "No such book.", Toast.LENGTH_LONG).show();
                        }

                    }
                });
                inputAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = inputAlert.create();
                alertDialog.show();

            }
        });
    }
}
