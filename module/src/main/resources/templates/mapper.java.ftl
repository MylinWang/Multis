
<#assign className = table.mapperName>

package ${package.Mapper};

import ${package.Entity}.${entity};
import org.apache.ibatis.annotations.*;
import java.math.BigInteger;
import java.util.List;

@Mapper
public interface ${className} {

// 根据ID查询操作
@Select("SELECT * FROM ${table.name} WHERE id =  <#noparse>#{</#noparse>${table.name}Id<#noparse>}</#noparse> AND is_deleted=0")
${entity} getById(BigInteger ${table.name}Id);

// 根据ID提取操作
@Select("SELECT * FROM ${table.name} WHERE id =  <#noparse>#{</#noparse>${table.name}Id<#noparse>}</#noparse>")
${entity} extractById(BigInteger ${table.name}Id);

// 插入操作
Integer insert(${entity} ${table.name});

// 更新操作
Integer update(${entity} ${table.name});

// 删除操作
@Update("UPDATE ${table.name} SET update_time = <#noparse>#{</#noparse>updateTime<#noparse>}</#noparse> , is_deleted = 1 WHERE id = <#noparse>#{</#noparse>${table.name}Id<#noparse>}</#noparse>")
Integer delete(@Param("updateTime") Integer updateTime, @Param("${table.name}Id")  BigInteger ${table.name}Id);

@Select("select * from ${table.name} where is_deleted=0")
List<${entity}> getAll();

}
