package klapper.custom_listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    SearchView searchView;
    ListView listView;
    List<String> my_list;
    CustomAdapter mCustomAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = (SearchView)findViewById(R.id.searchView);
        listView = (ListView)findViewById(R.id.listview);
        searchView.setIconifiedByDefault(false);// 設置直接可以輸入文字
        searchView.setFocusable(false); // 不要進畫面就跳出輸入鍵盤
        setSearch_function();

        my_list = new ArrayList<>();

        // 給List增加測試數據
        for(int i=0;i<1000;i++){
            my_list.add("no: "+i);
        }

        mCustomAdapter = new CustomAdapter(this,my_list);
        listView.setAdapter(mCustomAdapter);
        // 給listview 設置過濾器(暫時沒功能)
        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "You Choose"+ position+" listItem", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setSearch_function() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mCustomAdapter.getFilter().filter(newText);

                return true;
            }
        });

    }
}
