package net.undertaker.grimtales;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.undertaker.grimtales.block.ModBlocks;
import net.undertaker.grimtales.block.entity.ModBlockEntities;
import net.undertaker.grimtales.effect.ModEffects;
import net.undertaker.grimtales.enchantment.ModEnchantments;
import net.undertaker.grimtales.entity.ModEntities;
import net.undertaker.grimtales.entity.client.CapybaraRenderer;
import net.undertaker.grimtales.fluid.ModFluidBlocks;
import net.undertaker.grimtales.fluid.ModFluidTypes;
import net.undertaker.grimtales.fluid.ModFluids;
import net.undertaker.grimtales.item.ModCreativeTabs;
import net.undertaker.grimtales.item.ModItems;
import net.undertaker.grimtales.lootmodifiers.ModLootModifiers;
import net.undertaker.grimtales.networking.ModNetworkPackets;
import net.undertaker.grimtales.recipe.ModRecipes;
import net.undertaker.grimtales.screen.ModMenuTypes;
import net.undertaker.grimtales.screen.WorkstationScreen;
import net.undertaker.grimtales.sound.ModSounds;
import org.slf4j.Logger;

@Mod(GrimTales.MOD_ID)
public class GrimTales
{
    public static final String MOD_ID = "grimtales";
    private static final Logger LOGGER = LogUtils.getLogger();
    public GrimTales()
    {
    @SuppressWarnings("removal") IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModCreativeTabs.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModSounds.register(modEventBus);
        ModEffects.register(modEventBus);
        ModEnchantments.register(modEventBus);
        ModLootModifiers.register(modEventBus);
        ModFluidTypes.register(modEventBus);
        ModFluids.register(modEventBus);
        ModFluidBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModRecipes.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);

    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() ->
        {

        });
        ModNetworkPackets.register();
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_GOD_WATER.get(), RenderType.solid());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FlOWING_GOD_WATER.get(), RenderType.solid());
            EntityRenderers.register(ModEntities.CAPYBARA.get(), CapybaraRenderer::new);

            MenuScreens.register(ModMenuTypes.WORKSTATION_MENU.get(), WorkstationScreen::new);
        }
    }
    /**
     * 100 ФАКТОВ О ГНОМАХ-ХУЕКРАДАХ
     *
     * 1. Этой ночью гномы-хуекрады придут к тебе.
     * 2. Если гномы-хуекрады не придут к тебе этой ночью, придут следующей.
     * 3. Если они не придут следующей ночью - значит, они заняты и у них дела. Вы думали, хуи воровать - это лёгкая работёнка? Как бы не так.
     * 4. В любом случае, они знают твой адрес.
     * 5. Если ты девушка, гномы-хуекрады не придут к тебе. Даже не проси.
     * 6. Ну потому что они не дураки и знают, что у женщин нет хуёв.
     * 7. Гномы-хуекрады проникают в дома спящих людей через сливное отверстие в ванной.
     * 8. Их рост - 20 сантиметров. Не больше. Иногда - меньше.
     * 9. Они носят красные колпаки и очень ехидно хихикают.
     * 10. Они очень стараются не хихикать, когда подкрадываются на цыпочках к спящему человеку, но это не всегда получается.
     * 11. Если вы слышите сквозь сон мерзкое хихиканье - у меня для вас плохие новости.
     * 12. Многие спрашивают, как защититься от гномов-хуекрадов. Отвечаем: их отпугивает вантуз.
     * 13. Они всё-таки пробираются через сливное отверстие, и логично предположить, что к вантузам у них неприязнь.
     * 14. Вы можете заснуть с вантузом в руке или для верности сунуть его в трусы.
     * 15. Если вы считаете, что засыпать с вантузом в руке слишком глупо, гномы украдут ваш хуй.
     * 16. Если вы не верите в гномов-хуекрадов, я посмотрю на выражение вашего лица утром после ночи, когда они всё-таки придут.
     * 17. Вы наверняка подозрительно спросите: "Хмм, Александр, а откуда вы столько знаете о гномах-хуекрадах?"
     * Я читал.
     * 18. Никто доподлинно не знает, зачем им хуи. Есть две основные теории.
     * 19. Первая теория объясняет количество некачественной тушёнки в нашей стране. Да, вы правильно поняли.
     * 20. Согласно второй теории, им это просто по приколу.
     * 21. Говорят, они устраивают пляски и водят хороводы вокруг украденных хуёв, но это не точно.
     * 22. Забавно, но коты, всегда чувствительные ко всякой нечисти, обычно не замечают гномов-хуекрадов. Так что если у вас кот, это не спасет.
     * 23. Гномы-хуекрады - это радикальное движение, отколовшееся от более консервативных гномов, ворующих носки и трусы.
     * 24. Гномы, ворующие носки и трусы, презирают хуекрадов и считают их кончеными отморозками.
     * 25. Они, в общем-то, правы.
     * 26. Однажды гномы, ворующие нижнее белье, попросили Лигу Гномов запретить хуекрадам называть себя гномами, чтобы не позорить расу.
     * 27. Бедолаги не знали, что никакой Лиги Гномов не существует, потому что её только что придумал я.
     * 28. Многие спрашивают: есть ли у гномов-хуекрадов хуи?
     * Конечно, есть. Но очень маленькие.
     * 29. Меньше, чем бороды.
     * 30. У певца Павла Кашина есть песня про гномиков, танцующих на уютном столике. Он многое знал.
     * 31. Многим интересно, как именно гномы таскают хуи, если они сами ростом 20 сантиметров?
     * Вместе. Как бревно на субботнике.
     * 32. О том, каким образом гномы отделяют хуй от тела хозяина, мне запрещено рассказывать.
     * 33. Но это не больно.
     * Я читал.
     * 34. По свидетельствам жертв - никакой крови, никакой боли. Просто хуй отделяется от тела хозяина, как хвост у ящерицы.
     * 35. А потом эти мерзкие негодники, зловредно хихикая, уносят его в своё логово.
     * 36. Если вы услышали не просто хихиканье, а торжественное подхихикиванье с хохотушками и улюлюканьем - у меня для вас ОЧЕНЬ плохие новости.
     * 37. Это значит, что ваш мистер вялый уже играет роль бревна на маленьком гномьем субботнике.
     * 38. Можно ли вернуть украденный хуй обратно? Нам неизвестно, чтобы это кому-то удавалось, но теоретически, если узнать, где они живут, можно
     * 39. Беда в том, что никто доподлинно не знают, где они живут. Учёные полагают, что они обитают в подвалах жилых домов.
     * 40. Но до сих пор ни в одном подвале жилого дома не было обнаружено ни одного гнома-хуекрада или вязанки сворованных хуёв.
     * 41. Скорее всего, они просто очень быстро бегают.
     * 42. Бегают они не просто быстро, а ещё и бесшумно. В этом их преимущество - и ваша трагедия.
     * 43. Спрашивают: котируют ли хуекрады дилдо и страпоны. Отвечаем: нет.
     * 44. Один человек пытался так обмануть гномов, а они не только украли хуй, но ещё и надругались над беднягой.
     * 45. И ещё хихикали так противно.
     * 46. Некоторые после нападения гномов спрашивают: "Но ведь ничего страшного, хуй ведь отрастёт снова, да?"
     * Очень больно говорить им правду.
     * 47. Общество помощи жертвам гномов-хуекрадов существует с 1956 года.
     * 48. Я уже говорил, что они знают ваш домашний адрес, но будет не лишним напомнить.
     * 49. В книге о путешествии Гулливера в страну лилипутов автор упустил один момент.
     * 50. У гномов есть походная песенка. Она звучит так, исполняют её мерзкими, хихикающими, скрипуче-писклявыми голосками:
     * 51. Мы гномы-гномы-гномы, воруем мы хуи,
     * Однажды своровали у короля Луи,
     * У Цезаря, у Брута, Ивана Калиты,
     * А завтра вместе с ними окажешься и ты
     * 52. Так ли это - история умалчивает. Возможно, это просто гномьи мифы, не имеющие отношения к реальности.
     * 53. Но над последними словами я всё же рекомендую задуматься всем. Они не остановятся.
     * 54. Кстати, договориться с ними тоже невозможно. Мы немного знаем язык гномов, ворующих нижнее бельё, но у хуекрадов другой диалект.
     * 55. Фотографий хуекрадов не существует. Они очень чуткие: как только жертва начинает просыпаться, они тут же быстро разбегаются по углам.
     * 56. Один гном, убегая, потерял свою шапочку. Человек не заметил этого и лёг спать дальше. История закончилась печально.
     * Будьте внимательны!
     * 57. Кстати, спать ещё не хотите?
     * 58. В Австралии водятся сумчатые гномы-хуекрады.
     * 59. В США есть черные гномы-хуекрады. Они воруют не только хуи, но и телефоны, телевизоры и ноутбуки. Живут на пособие по безработице.
     * 60. Японские гномы-хуекрады по ночам обкладываются украденными хуями и сочиняют сценарии для аниме.
     * 61. Единственное место на Земле, где нет гномов-хуекрадов - это Сызрань. Ну подумайте сами, стали бы вы жить в Сызрани?
     * 62. Гномы-хуекрады разделяют идеи радикального феминизма. Они делают хуемразей просто мразями.
     * 63. Если вам кажется, что у вас сегодня хуёвый день - не расстраивайтесь. Завтрашний день может оказаться НЕХУЁВЫМ ХАХАХАХ
     * 64. *ba-dum-tsss*
     * 65. Многие спорят: есть ли некие сущности, ворующие хуи у гномов-хуекрадов? Этого мы не знаем.
     * 66. Тайной покрыт и вопрос их размножения. Они настолько больные ублюдки, что я не хочу даже думать об этом.
     * 67. Вы думаете, что я тут шутки шучу, ну да, ну да.
     * 68. А знаете, почему Гитлер был такой злой? То-то же.
     * 69. Не стоит думать, что гномы - это возмездие или наказание за плохие поступки. Им нужны только хуи. Им плевать, какой вы человек.
     * 70. Опять же, спрашивают, откуда я всё это знаю. Я не имею права раскрывать свои источники.
     * 71. Многие спрашивают: воруют ли гномы хуи у членодевок?
     * Воруют.
     * Но потом очень расстраиваются.
     * 72. Мне надо писать книгу, а потому отвлекусь от гномов, но скоро непременно вернусь к этой теме.
     * 73. Возможно, минувшей ночью они уже приходили к кому-то.
     * 74. Не путайте гномов-хуекрадов с дварфами-членоворами. Никаких дварфов-членоворов не существует, это всё выдумки.
     * 75. Если гномы украдут у вас хуй, в полиции вам не помогут. Только посмеются: "Ха-ха, гномы-хуекрады, гражданин, вы серьезно?"
     * 76. И только старый опер грустно вздохнет и нахмурится, но не подаст виду.
     * 77. Люди сначала такие "Хаха, гномы-хуекрады, как смешно, обоссаться", а потом "Блядь, кто-то украл мой хуй, кто бы это мог быть!"
     * 78. Если петь на мотив "Море, море": "Гномы, гномы хуй украли, на запчасти разобрали", это останется с вами на весь день. Не благодарите.
     * 79. Мы доподлинно не знаем, разбирают ли гномы хуи на запчасти и что они вообще с ними делают.
     * 80. Если у человека украсть хуй, он станет злым и раздражительным. Присмотритесь к своему окружению.
     * 81. Только осторожнее с шутками.
     * - Ха-ха, ты что такой злой, у тебя что, гномы хуй украли?
     * - БЛЯДЬ, ДА.
     * 82. Появилась версия, что гномы делают из украденных хуёв других людей.
     * Это многое объясняет в человеческом поведении.
     * 83. Пока вы читаете этот тред, они уже подбираются к вам.
     * 84. Смотрите, тут пробежал какой-то маленький гномик, НУ И ХУЙ С НИМ ХАХАХАХ
     * 85. За Северным полярным кругом тоже есть гномы-хуекрады. Они очень агрессивные, носят ушанки и радуются каждой добыче.
     * 86. Что там говорить, если гномы-хуекрады живут даже в Сыктавкаре.
     * Но не в Сызрани.
     * 87. Даже если вы моряк в дальнем плаваньи, опасайтесь: у гномов есть маленькие подводные лодки.
     * 88. Угадайте, что они хранят в торпедном отсеке.
     * 89. Однажды гномы-хуекрады попались на таможне. Их спрашивают: "Что у вас в ящиках?" А они: "Товарищ майор, да ни хуя там нет".
     * Лжецы.
     * 90. Ещё способ защититься от гномов: вы можете положить на свой хуй лицехвата из "Чужого". Гномы пиздец как срут кирпичами от этого фильма.
     * 91. Хмм, чот не очень способ, кажется.
     * 92. Окей, вы можете раскрасить хуй оранжевым, добавить зелени и замаскировать его под морковку!
     * 93. Но тогда могут прийти зайцы-хуегрызы, а это вообще очень плохое развитие событий.
     * 94. Просто спите в железных трусах. Гномы не пойдут в строительный магазин за болгаркой в три часа ночи.
     * 95. Говорят, что в диких джунглях Амазонки существуют ещё муравьеды-хуесосы. Это не опасно, но неприятно.
     * 96. Если гномов-хуекрадов не существует, то кто тогда пробирается в дом через сливное отверстие в ванной и крадёт хуи у мужчин? Шах и мат!
     * 97. Просыпаешься такой со странным чувством, думаешь спросонья: "Какого хуя?"
     * А УЖЕ НИКАКОГО ХАХАХАХАХ
     * 98. В девяностые была телепередача "В поисках утраченного". Изначально она рассказывала страшную правду о гномах, но её закрыли.
     * 99. Некоторые думают, что существуют гномы, ворующие у женщин сиськи. Полная чепуха, какой-то больной ублюдок придумал, а все повелись.
     * 100. За время, которое вы потратили на чтение этого треда, гномы по всему миру украли около пятидесяти хуёв.
     */
}
