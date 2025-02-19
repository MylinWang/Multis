package ${package.Service};
import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;
/**
* <p>
    * ${table.comment!} 服务实现类
    * </p>
*
* @author ${author}
* @since ${date}
*/
@Service
<#if kotlin>

<#else>
public class ${table.serviceName} {
    @Resource
    private ${table.mapperName} mapper;

    public ${entity} getById(BigInteger id) {
    return mapper.getById(id);
    }

    public ${entity} extractById(BigInteger id) {
    return mapper.extractById(id);
    }

    public List<${entity}> getAll() {
    return mapper.getAll();
    }

    //创建商品类目
    public int insert(${entity} ${entity?uncap_first}){

    return mapper.insert(${entity?uncap_first});
    }

    //更新商品类目
    public int update(${entity} ${entity?uncap_first}){

    return mapper.update(${entity?uncap_first});

    }

    // 删除商品类目
    public int delete(BigInteger id) {
    return mapper.delete( (int) (System.currentTimeMillis() / 1000),id);
    }
    }
</#if>
