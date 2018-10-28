package DAO;

import bean.Hero;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HeroDAO {
    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8", "root",
        "admin");
    }
    public HeroDAO(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public int getTotal(){
        int total = 0;
        try(Statement s = getConnection().createStatement()) {
            String sql = "SELECT COUNT(*) FROM hero";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()){
                total = rs.getInt(1);
            }
            System.out.println(total);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }
    public void add(Hero hero){
        String sql = "insert into hero values(null,?,?,?)";
        try(Connection c = getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,hero.name);
            ps.setFloat(2,hero.hp);
            ps.setInt(3,hero.damage);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void update(Hero hero){
        String sql = "UPDATE hero SET NAME =?,hp=?,damage=? where id =?";
        try (Connection s = getConnection();PreparedStatement ps = s.prepareStatement(sql)){
            ps.setString(1,hero.name);
            ps.setFloat(2,hero.hp);
            ps.setInt(3,hero.damage);
            ps.setInt(4,hero.id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Hero delete(int id){
        Hero hero = null;
        try (Connection c = getConnection();Statement s = c.createStatement()){
            String sql = "SELECT *FROM hero WHERE id = "+id;
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()){
            hero = new Hero();
            String name = rs.getString(2);
            float hp = rs.getFloat("hp");
            int damage = rs.getInt(4);
            hero.name = name;
            hero.hp = hp;
            hero.damage = damage;
            hero.id = id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hero;
    }
    public List<Hero> list(){
        return list(0,Short.MAX_VALUE);
    }

    private List<Hero> list(int start, int count) {
        List<Hero> heros = new ArrayList<>();
        String sql = "SELECT *FROM hero ORDER BY id DESC limit ?,?";
        try (Connection c = getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1,start);
            ps.setInt(2,count);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Hero hero = new Hero();
                int id = rs.getInt(1);
                String name = rs.getString(2);
                float hp = rs.getFloat("hp");
                int damage = rs.getInt(4);
                hero.id = id;
                hero.name = name;
                hero.hp = hp;
                hero.damage = damage;
                heros.add(hero);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return heros;
    }
}
