package zingg.snowpark.hash;

import com.snowflake.snowpark_java.Column;
import com.snowflake.snowpark_java.DataFrame;
import com.snowflake.snowpark_java.Row;
import com.snowflake.snowpark_java.Functions;
import com.snowflake.snowpark_java.udf.JavaUDF1;
import com.snowflake.snowpark_java.types.DataType;
import com.snowflake.snowpark_java.types.DataTypes;

import zingg.client.ZFrame;
import zingg.hash.LastWord;

public class SnowLastWord extends LastWord<DataFrame,Row,Column,DataType> implements JavaUDF1<String, String>{
	
	public SnowLastWord() {
		super();
		setDataType(DataTypes.StringType);
		setReturnType(DataTypes.StringType);
		
	}

	@Override
	public Object getAs(Row r, String column) {
		return (String) r.getAs(column);
	}



	@Override
	public ZFrame<DataFrame, Row, Column> apply(ZFrame<DataFrame, Row, Column> ds, String column,
			String newColumn) {
		return ds.withColumn(newColumn, Functions.callUDF(this.name, ds.col(column)));
	}

}
