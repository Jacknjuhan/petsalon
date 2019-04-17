import react,{Component} from 'react';
import display from '../assets/pet.png';
import {List,Avatar} from 'antd';
import styles from './index.css';
import Link from 'umi/link';
import {connect} from 'dva';


const mapStateToProps = (state) =>{
  return {
    list: state.list
  }
}

const mapDispatchToProps = (dispatch) =>{
  return {
    // click: ()=>dispatch({type:"typecount/click",payload:{photo:"clothes"}}),
    fetch: ()=>dispatch({type:"list/fetchData"})
  }
}

@connect(mapStateToProps,mapDispatchToProps)
class myList extends Component{
  componentDidMount(){
    this.props.fetch();
  }
  render(){
    const data = [
      {
        title: '添加Pet',
      },
      {
        title: '删除Pet',
      },
      {
        title: '编辑Pet信息',
      },
      {
        title: 'Pet列表',
      },
    ];
    //const data = this.props.list.data===undefined?[]:this.props.list.data;//incase data is not passed from backend
    return (
    <div className={styles.list}>
      <List
        itemLayout="horizontal"
        dataSource={data}
        renderItem={item => (
          <List.Item actions={[<Link to="/">Back to index</Link>]}>
        <List.Item.Meta
          //avatar={<Avatar src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png" />}
          title={<a href="https://ant.design">{item.title}</a>}
          //description="Ant Design, a design language for background applications, is refined by Ant UED Team"
        />
      </List.Item>
        )}
      />
    </div>
    )
    
  }
}

export default myList;