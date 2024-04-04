package africa.semicolon.Blog.Services;

import africa.semicolon.Blog.data.Model.User;
import africa.semicolon.Blog.data.Model.View;
import africa.semicolon.Blog.data.Repository.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static africa.semicolon.Blog.Utils.Mapper.map;

@Service
public class ViewServicesImpl implements ViewServices {
    @Autowired
    private Views views;

    @Override
    public View saveViewOf(User viewer) {
        View newView = map(viewer);
        return views.save(newView);
    }
}
