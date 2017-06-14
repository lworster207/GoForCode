/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

/**
 *
 * @author apprentice
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class ItemListDaoDbImpl implements ItemListDao {

    private static final String SQL_INSERT_ITEM
            = "insert into items "
            + "(product_id, itemName, price, quantity) "
            + "values (?, ?, ?, ?)";
    private static final String SQL_DELETE_ITEM
            = "delete from items where product_id = ?";

    private static final String SQL_SELECT_ITEM
            = "select * from items where product_id = ?";

    private static final String SQL_SET_QUANTITY
            = "update items set quantity = ? "
            + "where product_id = ?";

    private static final String SQL_UPDATE_ITEM
            = "update items set "
            + "quantity = ?, last_name = ?, company = ?, "
            + "phone = ?, email = ? "
            + "where product_id = ?";

    private static final String SQL_SELECT_ALL_ITEMS
            = "select * from items";
    private static final String SQL_SELECT_ALL_AVAILABLE_ITEMS
            = "select * from items where quantity > 0";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String getName(Item item) {
        return item.getName();
    }

    @Override
    public int getQuantity(Item item) {
        return item.getQuantity();
    }

    @Override
    public BigDecimal getPrice(Item item) {
        return item.getPrice();
    }

    @Override
    public List<Item> getAllItems() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ITEMS,
                new ItemMapper());

    }

    @Override
    public Item getItem(String itemId) {

        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ITEM,
                    new ItemMapper(), itemId);
        } catch (EmptyResultDataAccessException ex) {
            // there were no results for the given item id - we just
            // want to return null in this case
            return null;
        }
    }

    @Override
    public void setQuantity(Item item, int quantity) {

        jdbcTemplate.update(SQL_SET_QUANTITY,
                quantity, item.getItemId());

    }

    @Override
    public List<Item> getAllAvailableItems() {
        return jdbcTemplate.query(SQL_SELECT_ALL_AVAILABLE_ITEMS,
                new ItemMapper());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Item addItem(String itemId, Item item) {

        Item newItem = getItem(itemId);
        if (newItem == null) {
            jdbcTemplate.update(SQL_INSERT_ITEM,
                    item.getItemId(),
                    item.getName(),
                    item.getPrice(),
                    item.getQuantity());

            // query the database for the id that was just assigned to the new
            // row in the database
            int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                    Integer.class);
            // set the new id value on the item object and return it
            item.setDbItemId(newId);
        } else {
            item.setDbItemId(newItem.getDbItemId());
        }

        return item;
    }

    @Override
    public Item removeItem(String itemId
    ) {
        Item removedItem = getItem(itemId);
        jdbcTemplate.update(SQL_DELETE_ITEM, itemId);
        return removedItem;
    }

    private static final class ItemMapper implements RowMapper<Item> {

        public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
            Item item = new Item();
            item.setItemId(rs.getString("product_id"));
            item.setName(rs.getString("itemName"));
            item.setPrice(new BigDecimal(rs.getString("price")));
            item.setQuantity(rs.getInt("quantity"));
            return item;
        }

    }
}
