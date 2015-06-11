package com.jjb.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 14/12/12.
 */
public class DBManager {
	private MyDatabaseHelper helper;
	private SQLiteDatabase db;

	public DBManager(Context context) {
		helper = new MyDatabaseHelper(context, null); // 传入null使用默认factory初始化
		db = helper.getWritableDatabase();
	}

	/**
	 * 添加任务
	 * 
	 * @param itembean
	 */
	public void addItem(ItemBean item) {
		ContentValues cv = new ContentValues();
		// cv.put("id", plan.getId()); id is auto_increment, no need
		cv.put("userid", item.getUserId());
		cv.put("name", item.getName());
		cv.put("price", item.getPrice());
		cv.put("isout", item.isOut());
		cv.put("classify", item.getClassify());
		cv.put("time", item.getTime());

		db.insert("item_table", "null", cv);
	}

	/**
	 * 删除 by id
	 * 
	 * @param id
	 */
	public void deletePlan(int id) {
		String whereClause = "id=?";
		String[] whereArgs = { Integer.toString(id) };
		db.delete("item_table", whereClause, whereArgs);
	}

	/**
	 * 列出用户的item列表
	 * 
	 * @param userid
	 */
	public List<ItemBean> listItems(String userId) {

		Cursor c = db.query("item_table", null, "userid=?",
				new String[] { userId }, null, null, null);

		List<ItemBean> res = new ArrayList<ItemBean>();

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			ItemBean tmp = new ItemBean();
			tmp.setId(c.getLong(c.getColumnIndex("id")));
			tmp.setUserId(userId);
			tmp.setName(c.getString(c.getColumnIndex("name")));
			tmp.setPrice(c.getDouble(c.getColumnIndex("price")));
			tmp.setOut(c.getInt(c.getColumnIndex("isout")) == 1);
			tmp.setClassify(c.getInt(c.getColumnIndex("classify")));
			tmp.setTime(c.getString(c.getColumnIndex("time")));
			res.add(tmp);
		}
		return res;
	}

	/**
	 * 查询某时间段内的item列表
	 * 
	 * @param userId
	 * @param fromDatetime
	 * @param toDatetime
	 */
	public List<ItemBean> listItemsByTime(String userId, String fromDatetime,
			String toDatetime) {

		Cursor c = db.query("item_table", null,
				"userid=? and date(time)>=date(?) and date(time)<=date(?)", new String[] { userId,
						fromDatetime, toDatetime}, null, null, "date(time)");

		List<ItemBean> res = new ArrayList<ItemBean>();

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			ItemBean tmp = new ItemBean();
			tmp.setId(c.getLong(c.getColumnIndex("id")));
			tmp.setUserId(userId);
			tmp.setName(c.getString(c.getColumnIndex("name")));
			tmp.setPrice(c.getDouble(c.getColumnIndex("price")));
			tmp.setOut(c.getInt(c.getColumnIndex("isout")) == 1);
			tmp.setClassify(c.getInt(c.getColumnIndex("classify")));
			tmp.setTime(c.getString(c.getColumnIndex("time")));
			res.add(tmp);
		}
		return res;
	}

	/**
	 * 查询单条任务纪录
	 */
	public ItemBean selectItem(int id) {
		ItemBean res = null;
		Cursor c = db.query("item_table", null, "id=?",
				new String[] { Integer.toString(id) }, null, null, null);

		if (c.moveToFirst()) { // 是否为空
			res = new ItemBean();
			res.setId(id);
			res.setUserId(c.getString(c.getColumnIndex("userid")));
			res.setName(c.getString(c.getColumnIndex("name")));
			res.setPrice(c.getDouble(c.getColumnIndex("price")));
			res.setOut(c.getInt(c.getColumnIndex("isout")) == 1);
			res.setClassify(c.getInt(c.getColumnIndex("classify")));
			res.setTime(c.getString(c.getColumnIndex("time")));
		}
		return res;
	}

	/**
	 * 更新
	 */
	public void updateItem(int id, ItemBean item) {
		ContentValues cv = new ContentValues();
		cv.put("userid", item.getUserId());
		cv.put("name", item.getName());
		cv.put("price", item.getPrice());
		cv.put("isout", item.isOut());
		cv.put("classify", item.getClassify());
		cv.put("time", item.getTime());

		String whereClause = "id=?";
		String[] whereArgs = { Integer.toString(id) };

		db.update("item_table", cv, whereClause, whereArgs);
	}

	/*
	 * test 删除数据表 重新建表 测试的时候可以用
	 */
	public void refreshPlanTable() {
		db.execSQL("drop table plan_table");
		db.execSQL("CREATE TABLE `item_table` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `userid` VARCHAR(255), `name` NVARCHAR(255), `price` DOUBLE, `isout` BIT, `classify` INT, `time` DATETIME, `isfinish` INT);");
	}
}
