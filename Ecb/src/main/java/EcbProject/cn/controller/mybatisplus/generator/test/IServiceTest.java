package EcbProject.cn.controller.mybatisplus.generator.test;

import EcbProject.cn.controller.mybatisplus.generator.entity.HurcProjectinfo;
import EcbProject.cn.controller.mybatisplus.generator.service.IHurcProjectinfoService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.*;
import java.util.function.Function;

public class IServiceTest implements IHurcProjectinfoService {

    @Override
    public boolean saveBatch(Collection<HurcProjectinfo> entityList, int batchSize) {
        return this.saveBatch(entityList);
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<HurcProjectinfo> entityList, int batchSize) {
        return this.saveOrUpdateBatch(entityList);
    }

    @Override
    public boolean updateBatchById(Collection<HurcProjectinfo> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(HurcProjectinfo entity) {
        return false;
    }

    @Override
    public HurcProjectinfo getOne(Wrapper<HurcProjectinfo> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Optional<HurcProjectinfo> getOneOpt(Wrapper<HurcProjectinfo> queryWrapper, boolean throwEx) {
        return Optional.empty();
    }

    @Override
    public Map<String, Object> getMap(Wrapper<HurcProjectinfo> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<HurcProjectinfo> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<HurcProjectinfo> getBaseMapper() {
        return null;
    }

    @Override
    public Class<HurcProjectinfo> getEntityClass() {
        return null;
    }

//    public static void main(String[] args) {
//        Random random = new Random();
//        Integer count = random.nextInt(5) + 2;
//        System.out.println("字符串个数：" + count);
//
//        for(int i = 0;i < count;i++){
//            //生成一个随机字符串
//            StringBuilder sb = new StringBuilder();
//            String characters = "abcdefghijklmnopqrstuvwxyz";
//            Integer length = random.nextInt(20) + 2;
//            for (int j = 0; j < length; j++) {
//                int index = random.nextInt(characters.length());
//                sb.append(characters.charAt(index));
//            }
//            System.out.println(sb);
//            //统计字符串每个char出现的次数
//            char[] chars = sb.toString().toCharArray();
//            HashMap<Character,Integer> chatMap = new HashMap<>();
//            for (char aChar : chars) {
//                if(chatMap.containsKey(aChar)){
//                    chatMap.put(aChar, chatMap.get(aChar) + 1);
//                } else {
//                    chatMap.put(aChar, 1);
//                }
//            }
//            //对map的value进行排序
//            List<Map.Entry<Character, Integer>> list = new ArrayList<>(chatMap.entrySet());
//            list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
//            System.out.println("排序后的map：" + list);
//            //计算漂亮值
//            Integer prettyValue = 26;
//            Integer totalScore = 0;
//            for (Map.Entry<Character, Integer> characterIntegerEntry : list) {
//                totalScore = totalScore + characterIntegerEntry.getValue() * prettyValue;
//                prettyValue--;
//            }
//            System.out.println("漂亮值：" + totalScore);
//        }
public static void main(String[] args) {
    HashMap<String,Object> map = new HashMap<>();
    map.put("key1","value1");
    map.forEach((k,v) -> {
        k = "123";
        v = "456";
    });
    System.out.println(map);
}
    }


