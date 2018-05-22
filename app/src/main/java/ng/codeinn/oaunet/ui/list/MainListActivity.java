package ng.codeinn.oaunet.ui.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import ng.codeinn.oaunet.R;
import ng.codeinn.oaunet.ui.detail.DetailActivity;
import ng.codeinn.oaunet.ui.links.DepartmentsFragment;
import ng.codeinn.oaunet.ui.links.EportalFragment;
import ng.codeinn.oaunet.ui.links.NetQFragment;
import ng.codeinn.oaunet.ui.links.TranscriptFragment;
import ng.codeinn.oaunet.utilities.Constants;

public class MainListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ListItemFragment.OnItemClickListener {

    private static final String CURRENT_FILTERING_KEY = "CURRENT_FILTERING_KEY";
    private ListItemFragment mListItemFragment;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.setStatusBarBackground(R.color.colorPrimaryDark);
        NavigationView navigationView = findViewById(R.id.nav_view);
        Menu navMenu = navigationView.getMenu();
        MenuItem links = navMenu.findItem(R.id.links);
        navigationView.setItemIconTintList(null);
        setupDrawerContent(navigationView);
        setTextColorForMenuItem(links, R.color.colorIcons);

        mListItemFragment = (ListItemFragment)
                getSupportFragmentManager().findFragmentById(R.id.items_container);

        if(mListItemFragment == null){
            mListItemFragment = ListItemFragment.newInstance(Constants.NEWS_ITEM);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.items_container, mListItemFragment);
            transaction.commit();
        }

    }

    public void setActionBarTitle(String title){
        Objects.requireNonNull(getSupportActionBar()).setTitle(title);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_list_activity1, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {

            drawer.openDrawer(GravityCompat.START);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void setupDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            resetAllMenuItemsTextColor(navigationView);

            switch (id) {
                case R.id.nav_news: {
                    ListItemFragment listItemFragment =
                            ListItemFragment.newInstance(Constants.NEWS_ITEM);
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.items_container, listItemFragment);
                    transaction.commit();
                    break;
                }
                case R.id.nav_events: {
                    ListItemFragment listItemFragment =
                            ListItemFragment.newInstance(Constants.EVENT_ITEM);
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.items_container, listItemFragment);
                    transaction.commit();
                    break;
                }
                case R.id.nav_researches: {
                    ListItemFragment listItemFragment =
                            ListItemFragment.newInstance(Constants.RESEARCH_ITEM);
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.items_container, listItemFragment);
                    transaction.commit();
                    break;
                }
                case R.id.link_departments:{
                    DepartmentsFragment departmentsFragment =
                            DepartmentsFragment.newInstance();
                    FragmentTransaction departmentTransaction = getSupportFragmentManager().beginTransaction();
                    departmentTransaction.replace(R.id.items_container, departmentsFragment);
                    departmentTransaction.commit();
                    break;

                }
                case R.id.link_e_portal:{
                    EportalFragment eportalFragment =
                            EportalFragment.newInstance();
                    FragmentTransaction eportalTransaction = getSupportFragmentManager().beginTransaction();
                    eportalTransaction.replace(R.id.items_container, eportalFragment);
                    eportalTransaction.commit();
                    break;
                }
                case R.id.link_netque:{
                    NetQFragment netQFragment =
                            NetQFragment.newInstance();
                    FragmentTransaction netQTransaction = getSupportFragmentManager().beginTransaction();
                    netQTransaction.replace(R.id.items_container, netQFragment);
                    netQTransaction.commit();
                    break;
                }
                case R.id.link_transcript:{
                    TranscriptFragment transcriptFragment =
                            TranscriptFragment.newInstance();
                    FragmentTransaction transcriptTransaction = getSupportFragmentManager().beginTransaction();
                    transcriptTransaction.replace(R.id.items_container, transcriptFragment);
                    transcriptTransaction.commit();
                    break;
                }
            }
            item.setChecked(true);
            drawer.closeDrawers();
            return true;
        });
    }

    private void setTextColorForMenuItem(MenuItem menuItem, @ColorRes int color) {
        SpannableString spanString = new SpannableString(menuItem.getTitle().toString());
        spanString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, color)), 0, spanString.length(), 0);
        menuItem.setTitle(spanString);
    }

    private void resetAllMenuItemsTextColor(NavigationView navigationView) {
        for (int i = 0; i < navigationView.getMenu().size(); i++)
            setTextColorForMenuItem(navigationView.getMenu().getItem(i), R.color.colorIcons);
    }

    @Override
    public void onItemSelected(String link) {
        Intent itemDetailIntent = new Intent(MainListActivity.this, DetailActivity.class);
        itemDetailIntent.putExtra(DetailActivity.ITEM_LINK_EXTRA, link);
        startActivity(itemDetailIntent);
    }
}
