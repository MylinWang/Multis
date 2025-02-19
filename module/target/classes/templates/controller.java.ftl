package ${package.Controller};

import org.springframework.web.bind.annotation.RequestMapping;
<#if restControllerStyle>
    import org.springframework.web.bind.annotation.RestController;
<#else>
    import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
    import ${superControllerClassPackage};
</#if>
import com.gelin.domain.${entity}Vo;
import com.gelin.domain.ResultVo;
import com.gelin.entity.${entity};
import com.gelin.service.${entity}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
/**
* <p>
    * ${table.comment!} 前端控制器
    * </p>
*
* @author ${author}
* @since ${date}
*/
<#if restControllerStyle>
    @RestController
<#else>
</#if>
<#if kotlin>
    class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
    <#if superControllerClass??>
        public class ${table.controllerName} extends ${superControllerClass} {
    <#else>
        public class ${table.controllerName} {
    </#if>
    @Autowired
    private ${table.serviceName} ${table.name}Service;

    @RequestMapping("/category/detail")
    public ${entity}Vo detail(@RequestParam("id") BigInteger id){
    ${entity} ${table.name}  = ${table.name}Service.getById(id);
    ${entity}Vo ${table.name}Vo=new CategoryVo();
    ${table.name}Vo.setId(${table.name}.getId());
    ${table.name}Vo.setName(${table.name}.getName());
    ${table.name}Vo.setImage(${table.name}.getImage());
    return ${table.name}Vo;
    }

    @RequestMapping("/category/insert")
    public ResultVo insert(@RequestParam("name") String name,
    @RequestParam("image") String image) {
    ResultVo resultVo = new ResultVo();
    name = name.trim();
    if (name.equals("")) {
    resultVo.setMessage("name不能为空");
    return resultVo;
    }
    int timestamp = (int) (System.currentTimeMillis() / 1000);
    ${entity} ${table.name}=new ${entity}();
    ${table.name}.setName(name);
    ${table.name}.setImage(image);
    ${table.name}.setCreateTime(timestamp);
    ${table.name}.setUpdateTime(timestamp);
    try {
    Integer insert = ${table.name}Service.insert(category);
    String s = insert > 0 ? "新增成功" : "新增失败";
    resultVo.setMessage(s);
    } catch (Exception e) {
    resultVo.setMessage(e.getMessage());
    }
    return resultVo;
    }

    @RequestMapping("/category/update")
    public ResultVo update(@RequestParam("id") Long id,
    @RequestParam("name") String name,
    @RequestParam("image") String image) {
    ResultVo resultVo = new ResultVo();
    name = name.trim();
    if (name.equals("")) {
    resultVo.setMessage("name不能为空");
    return resultVo;
    }
    int timestamp = (int) (System.currentTimeMillis() / 1000);
    ${entity} ${table.name}=new ${entity}();
    ${table.name}.setId(id);
    ${table.name}.setName(name);
    ${table.name}.setImage(image);
    ${table.name}.setUpdateTime(timestamp);
    try{
    Integer update =  ${table.name}Service.update(category);
    String s = update>0 ? "新增成功" : "新增失败";
    resultVo.setMessage(s);
    }catch (Exception e){
    resultVo.setMessage(e.getMessage());
    }
    return resultVo;
    }

    @RequestMapping("/category/delete")
    public ResultVo delete(@RequestParam("id") BigInteger id){
    ResultVo resultVo = new ResultVo();
    int delete =  ${table.name}Service.delete(id);
    String s = delete>0 ? "新增成功" : "新增失败";
    resultVo.setMessage(s);
    return resultVo;
    }



    }
</#if>

