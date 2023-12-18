1. Add JSF facet.   
<img width="1108" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/f1136e7f-7c43-42df-8d84-6476170b3aa3">   

2. After adding JSF facet, you will be requested to configure JSF library. You don't need to prepare your own JSF library if you deploy the app on WebSphere since it's JSF runtime. So you just need to configure web.xml and faces-config.xml.       
<img width="636" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/5a83cee3-ffc7-4051-9da3-7541efd2cf87">

3. The page sequence can be defined as navigation rule.   
<img width="1363" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/a8470598-dc78-4ea3-b55e-5c37e6bafa4c">   

4. The managed bean is also defined in faces-config.   
<img width="637" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/ba1ad218-d3ed-412c-8a4a-10f67b295e68">   

This application simply accepts a client input in index.xhtml. These input values are bound to the managed bean so that the value can be retrieved in welcom.xhtml. 
